package com.github.tgiachi.sofa.sofaserver.dao;

import com.github.tgiachi.sofa.sofaserver.dao.base.BaseDao;
import com.github.tgiachi.sofa.sofaserver.entities.UnTrackedEntity;
import com.github.tgiachi.sofa.sofaserver.repository.UnTrackedRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UnTrackedDao extends BaseDao<UnTrackedEntity, UnTrackedRepository> {
    public UnTrackedDao(UnTrackedRepository repository) {
        super(repository);
    }

    public UnTrackedEntity insert(UnTrackedEntity entity) {
        try {
            var ent = repository.findByHashId(entity.getHashId());
            if (ent != null) {
                semaphore.release();
                return ent;
            }

            semaphore.release();
            entity.setUpdatedDateTime(LocalDateTime.now());
            entity.setCreatedDateTime(LocalDateTime.now());
            repository.save(entity);
            semaphore.release();
            return entity;
        } catch (Exception ex) {
            logger.error("Error during insert unTracked entity", ex);
            semaphore.release();
            return null;
        }
    }
}
