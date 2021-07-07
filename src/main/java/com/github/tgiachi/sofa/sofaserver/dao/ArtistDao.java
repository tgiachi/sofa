package com.github.tgiachi.sofa.sofaserver.dao;

import com.github.tgiachi.sofa.sofaserver.dao.base.BaseDao;
import com.github.tgiachi.sofa.sofaserver.entities.ArtistEntity;
import com.github.tgiachi.sofa.sofaserver.repository.ArtistRepository;
import com.github.tgiachi.sofa.sofaserver.utils.Md5Utils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ArtistDao extends BaseDao<ArtistEntity, ArtistRepository> {

    public ArtistDao(ArtistRepository repository) {
        super(repository);
    }

    @Override
    public ArtistEntity insert(ArtistEntity entity) {
        try {
            semaphore.acquire();

            var ent = repository.findByName(entity.getName());
            if (ent == null) {
                entity.setHashId(Md5Utils.md5(entity.getName()));
                entity.setCreatedDateTime(LocalDateTime.now());
                entity.setUpdateDataTime(LocalDateTime.now());
                repository.save(entity);
                semaphore.release();
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


}
