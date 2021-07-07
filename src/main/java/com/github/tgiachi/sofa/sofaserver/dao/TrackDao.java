package com.github.tgiachi.sofa.sofaserver.dao;

import com.github.tgiachi.sofa.sofaserver.dao.base.BaseDao;
import com.github.tgiachi.sofa.sofaserver.entities.TrackEntity;
import com.github.tgiachi.sofa.sofaserver.repository.TrackRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TrackDao extends BaseDao<TrackEntity, TrackRepository> {

    public TrackDao(TrackRepository repository) {
        super(repository);
    }

    public TrackEntity findByHashId(String hashId) {
        return repository.findByHashId(hashId);
    }

    public TrackEntity update(TrackEntity entity) {
        repository.save(entity);
        return entity;
    }

    public TrackEntity insert(TrackEntity entity) {

        try {
            semaphore.acquire();
            var track = repository.findByAlbumAndTrackName(entity.getAlbum(), entity.getTrackName());
            if (track != null) {
                semaphore.release();
                return track;
            }

            entity.setCreatedDateTime(LocalDateTime.now());
            entity.setUpdateDataTime(LocalDateTime.now());
            repository.save(entity);

            semaphore.release();

            return entity;
        } catch (Exception ex) {
            logger.error("Error during add track: ", ex);
            semaphore.release();
            return null;
        }

    }
}
