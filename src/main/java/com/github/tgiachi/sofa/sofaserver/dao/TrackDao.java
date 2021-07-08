package com.github.tgiachi.sofa.sofaserver.dao;

import com.github.tgiachi.sofa.sofaserver.dao.base.BaseDao;
import com.github.tgiachi.sofa.sofaserver.entities.TrackEntity;
import com.github.tgiachi.sofa.sofaserver.events.TrackAddedEvent;
import com.github.tgiachi.sofa.sofaserver.repository.GenreRepository;
import com.github.tgiachi.sofa.sofaserver.repository.TrackRepository;
import org.greenrobot.eventbus.EventBus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TrackDao extends BaseDao<TrackEntity, TrackRepository> {

    private final GenreRepository genreRepository;

    public TrackDao(TrackRepository repository, GenreRepository genreRepository) {
        super(repository);
        this.genreRepository = genreRepository;
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
            entity.setUpdatedDateTime(LocalDateTime.now());

            repository.save(entity);

            semaphore.release();

            EventBus.getDefault().post(TrackAddedEvent.builder().id(entity.getId()).build());

            return entity;
        } catch (Exception ex) {
            logger.error("Error during add track: ", ex);
            semaphore.release();
            return null;
        }

    }
}
