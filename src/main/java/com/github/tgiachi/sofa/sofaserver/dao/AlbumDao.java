package com.github.tgiachi.sofa.sofaserver.dao;

import com.github.tgiachi.sofa.sofaserver.dao.base.BaseDao;
import com.github.tgiachi.sofa.sofaserver.entities.AlbumEntity;
import com.github.tgiachi.sofa.sofaserver.events.AlbumAddedEvent;
import com.github.tgiachi.sofa.sofaserver.interfaces.dao.IBaseDao;
import com.github.tgiachi.sofa.sofaserver.repository.AlbumRepository;
import com.github.tgiachi.sofa.sofaserver.utils.Md5Utils;
import org.greenrobot.eventbus.EventBus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AlbumDao extends BaseDao<AlbumEntity, AlbumRepository> implements IBaseDao<AlbumEntity, CrudRepository<AlbumEntity, Long>> {

    public AlbumDao(AlbumRepository repository) {
        super(repository);
    }

    public AlbumEntity findById(Long id) {
        return repository.findById(id).get();
    }

    public AlbumEntity insert(AlbumEntity albumEntity) {
        try {
            semaphore.acquire();
            var album = repository.findByArtistAndName(albumEntity.getArtist(), albumEntity.getName());
            if (album != null) {
                semaphore.release();
                return album;
            }
            albumEntity.setCreatedDateTime(LocalDateTime.now());
            albumEntity.setUpdatedDateTime(LocalDateTime.now());
            albumEntity.setHashId(Md5Utils.md5(albumEntity.getName()));
            repository.save(albumEntity);

            semaphore.release();
            EventBus.getDefault().post(AlbumAddedEvent.builder().id(albumEntity.getId()).build());
            return albumEntity;

        } catch (Exception ex) {
            logger.error("Error during insert album", ex);
            semaphore.release();
            return null;
        }

    }

    @Override
    public AlbumEntity findByHashId(String hashId) {
        return repository.getByHashId(hashId);
    }
}
