package com.github.tgiachi.sofa.sofaserver.dao;

import com.github.tgiachi.sofa.sofaserver.dao.base.BaseDao;
import com.github.tgiachi.sofa.sofaserver.entities.AlbumEntity;
import com.github.tgiachi.sofa.sofaserver.entities.ArtistEntity;
import com.github.tgiachi.sofa.sofaserver.events.AlbumAddedEvent;
import com.github.tgiachi.sofa.sofaserver.events.ArtistAddedEvent;
import com.github.tgiachi.sofa.sofaserver.interfaces.dao.IBaseDao;
import com.github.tgiachi.sofa.sofaserver.repository.AlbumRepository;
import com.github.tgiachi.sofa.sofaserver.repository.ArtistRepository;
import com.github.tgiachi.sofa.sofaserver.utils.Md5Utils;
import org.greenrobot.eventbus.EventBus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ArtistDao extends BaseDao<ArtistEntity, ArtistRepository> implements IBaseDao<ArtistEntity, CrudRepository<ArtistEntity, Long>> {

    private final AlbumRepository albumRepository;

    public ArtistDao(ArtistRepository repository, AlbumRepository albumRepository) {
        super(repository);
        this.albumRepository = albumRepository;
    }

    @Override
    public ArtistEntity insert(ArtistEntity entity) {
        try {
            semaphore.acquire();

            var ent = repository.findByName(entity.getName());
            if (ent == null) {
                entity.setHashId(Md5Utils.md5(entity.getName()));
                entity.setCreatedDateTime(LocalDateTime.now());
                entity.setUpdatedDateTime(LocalDateTime.now());
                repository.save(entity);
                semaphore.release();
                EventBus.getDefault().post(ArtistAddedEvent.builder().id(entity.getId()).build());
                return entity;
            }
            semaphore.release();
            return ent;
        } catch (Exception ex) {
            logger.error("Error during insert into artits", ex);
            semaphore.release();
            return entity;
        }
    }

    @Override
    public ArtistEntity findByHashId(String hashId) {
        return repository.getByHashId(hashId);
    }


    public ArtistEntity findArtistByName(String artist) {
        return repository.findByName(artist);
    }

    public List<AlbumEntity> findArtistAlbums(String hashId) {
        var artist = repository.getByHashId(hashId);

        return albumRepository.findAlbumEntitiesByArtist(artist);

    }
}
