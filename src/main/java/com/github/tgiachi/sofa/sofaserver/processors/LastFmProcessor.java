package com.github.tgiachi.sofa.sofaserver.processors;

import com.github.tgiachi.sofa.sofaserver.events.AlbumAddedEvent;
import com.github.tgiachi.sofa.sofaserver.events.ArtistAddedEvent;
import com.github.tgiachi.sofa.sofaserver.events.TrackAddedEvent;
import com.github.tgiachi.sofa.sofaserver.repository.AlbumRepository;
import com.github.tgiachi.sofa.sofaserver.repository.ArtistRepository;
import com.github.tgiachi.sofa.sofaserver.repository.TrackRepository;
import de.umass.lastfm.*;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LastFmProcessor {

    private final AlbumRepository albumDao;
    private final ArtistRepository artistRepository;
    private final TrackRepository trackRepository;

    @Value("${lastfm.api}")
    private String lastFmApi;

    @Value("${lastfm.secret}")
    private String lastFmSecret;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public LastFmProcessor(AlbumRepository albumDao, ArtistRepository artistRepository, TrackRepository trackRepository) {
        this.albumDao = albumDao;
        this.artistRepository = artistRepository;
        this.trackRepository = trackRepository;
    }

    @PostConstruct
    public void onStart() {
        Caller.getInstance().setUserAgent("sofa");

        EventBus.getDefault().register(this);
    }

    public List<String> getSimilarArtist(String name) {

        return Artist.getSimilar(name, lastFmApi).stream().map(MusicEntry::getName).collect(Collectors.toList());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onArtistAdded(ArtistAddedEvent event) {
        var entity = artistRepository.findById(event.getId());
        if (entity.isPresent()) {
            if (!entity.get().getName().equals("")) {
                var artistLastFm = Artist.search(entity.get().getName(), lastFmApi).stream().findFirst();
                if (artistLastFm.isPresent()) {
                    var image = artistLastFm.get().getImageURL(ImageSize.LARGE);
                    entity.get().setMDbId(artistLastFm.get().getMbid());
                    entity.get().setCoverUrl(image);
                    artistRepository.save(entity.get());
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onTrackAdded(TrackAddedEvent event) {
        var entity = trackRepository.findById(event.getId());
        if (entity.isPresent()) {
            var trackLastFM = Track.getInfo(entity.get().getArtistName(), entity.get().getTrackName(), lastFmApi);
            if (trackLastFM != null) {
                entity.get().setMDbId(trackLastFM.getMbid());
                entity.get().setGlobalPlayCount(trackLastFM.getPlaycount());
                trackRepository.save(entity.get());
            }

        }
    }

    public Artist findArtistByName(String name) {
        var apiResult = Artist.search(name, lastFmApi);
        var artists = apiResult.stream().collect(Collectors.toUnmodifiableList());
        if (artists.isEmpty())
            return null;

        return artists.stream().findFirst().get();
    }

    public String correctArtistName(String name) {
        var apiResult = Artist.getCorrection(name, lastFmApi);
        if (apiResult != null) {
            return apiResult.getName();
        }
        return name;
    }

    public List<String> findArtistImage(String artistId) {
        return Artist.getImages(artistId, lastFmApi).getPageResults().stream().map(s -> s.getUrl()).collect(Collectors.toList());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onAlbumAdded(AlbumAddedEvent event) {
        var entity = albumDao.findById(event.getId()).get();

        if (entity.getName() != null && !entity.getName().equals("")) {

            var albumInfo = Album.getInfo(entity.getArtist().getName(), entity.getName(), lastFmApi);

            if (albumInfo != null) {
                var url = albumInfo.getImageURL(ImageSize.LARGE);
                entity.setMDbId(albumInfo.getMbid());
                entity.setGlobalPlayCount(albumInfo.getPlaycount());
                if (albumInfo.getReleaseDate() != null) {
                    var calendar = Calendar.getInstance();
                    calendar.setTime(albumInfo.getReleaseDate());
                    entity.setYear(Integer.toString(calendar.get(Calendar.YEAR)));
                }
                entity.setCoverUrl(url);
                albumDao.save(entity);
            }
        }
    }
}
