package com.github.tgiachi.sofa.sofaserver.dao;

import com.github.tgiachi.sofa.sofaserver.entities.ArtistEntity;
import com.github.tgiachi.sofa.sofaserver.repository.ArtistRepository;
import com.github.tgiachi.sofa.sofaserver.utils.Md5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.Semaphore;

@Component
public class ArtistDao {

    private Semaphore semaphore = new Semaphore(1);
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ArtistRepository artistRepository;

    public ArtistDao(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void insert(ArtistEntity entity) {
        try {
            semaphore.acquire();

            var ent = artistRepository.findByName(entity.getName());
            if (ent == null) {
                entity.setHashId(Md5Utils.md5(entity.getName()));
                entity.setCreatedDateTime(LocalDateTime.now());
                entity.setUpdateDataTime(LocalDateTime.now());
                artistRepository.save(entity);
            }
            semaphore.release();
        } catch (Exception ex) {
            logger.error("Error during insert into artits", ex);
        }
    }

}
