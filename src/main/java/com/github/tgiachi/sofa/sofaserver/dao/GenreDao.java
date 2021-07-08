package com.github.tgiachi.sofa.sofaserver.dao;

import com.github.tgiachi.sofa.sofaserver.dao.base.BaseDao;
import com.github.tgiachi.sofa.sofaserver.entities.GenreEntity;
import com.github.tgiachi.sofa.sofaserver.repository.GenreRepository;
import com.github.tgiachi.sofa.sofaserver.utils.Md5Utils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GenreDao extends BaseDao<GenreEntity, GenreRepository> {

    public GenreDao(GenreRepository repository) {
        super(repository);
    }


    public GenreEntity insert(GenreEntity entity) {
        try {
            semaphore.acquire();
            entity.setName(entity.getName().toUpperCase());

            var ent = repository.findByName(entity.getName().toUpperCase());
            if (ent != null) {
                semaphore.release();
                return ent;
            }

            entity.setCreatedDateTime(LocalDateTime.now());
            entity.setUpdatedDateTime(LocalDateTime.now());
            entity.setHashId(Md5Utils.md5(entity.getName().toUpperCase()));

            repository.save(entity);

            semaphore.release();

            return entity;

        } catch (Exception ex) {
            logger.info("Error during insert genre", ex);
            semaphore.release();
            return null;
        }
    }

}
