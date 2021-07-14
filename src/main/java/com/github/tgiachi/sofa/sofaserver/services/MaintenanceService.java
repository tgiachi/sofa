package com.github.tgiachi.sofa.sofaserver.services;

import com.github.tgiachi.sofa.sofaserver.entities.ArtistEntity;
import com.github.tgiachi.sofa.sofaserver.processors.LastFmProcessor;
import com.github.tgiachi.sofa.sofaserver.repository.ArtistRepository;
import com.github.tgiachi.sofa.sofaserver.services.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MaintenanceService extends BaseService {
    private final ArtistRepository artistRepository;
    ExecutorService executor = Executors.newFixedThreadPool(1);
    private final LastFmProcessor lastFmProcessor;


    public MaintenanceService(ArtistRepository artistRepository, LastFmProcessor lastFmProcessor) {
        this.artistRepository = artistRepository;

        this.lastFmProcessor = lastFmProcessor;
    }

    public void updateAllArtists() {
        var allArtists = artistRepository.findAll();
        for (var artist : allArtists) {
            executor.execute(updateArtist(artist));
        }
    }

    private Runnable updateArtist(ArtistEntity entity) {
        return () -> {
            try {
                var artist = lastFmProcessor.findArtistByName(entity.getName());
                if (artist != null) {
                    entity.setName(lastFmProcessor.correctArtistName(artist.getName()));
                    var images = new ArrayList<>(artist.availableSizes());

                    if (!images.isEmpty()) {
                        entity.setCoverUrl(artist.getImageURL(images.get(0)));
                        artistRepository.save(entity);
                        logger.info("Update artists {}", entity.getName());
                    }
                }

            } catch (Exception ex) {
                logger.error("Error during update artist: {}", entity.getName(), ex);
            }
        };
    }

}
