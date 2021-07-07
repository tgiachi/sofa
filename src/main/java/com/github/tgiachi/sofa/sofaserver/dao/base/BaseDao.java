package com.github.tgiachi.sofa.sofaserver.dao.base;

import com.github.tgiachi.sofa.sofaserver.entities.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.concurrent.Semaphore;

public abstract class BaseDao<TEntity extends BaseEntity, TRepository extends CrudRepository<TEntity, Long>> {

    protected Semaphore semaphore = new Semaphore(1);
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected TRepository repository;

    public BaseDao(TRepository repository) {
        this.repository = repository;
    }

    public TEntity insert(TEntity entity) {
        try {
            semaphore.acquire();
            entity.setCreatedDateTime(LocalDateTime.now());
            entity.setUpdateDataTime(LocalDateTime.now());

            repository.save(entity);

            semaphore.release();
            return entity;
        } catch (Exception ex) {
            semaphore.release();
            logger.error("Error during insert: ", ex);
            return null;
        }
    }

    public TEntity update(TEntity entity) {
        try {
            semaphore.acquire();
            entity.setUpdateDataTime(LocalDateTime.now());

            repository.save(entity);
            semaphore.release();
            return entity;
        } catch (Exception ex) {
            semaphore.release();
            logger.error("Error during insert: ", ex);
        }
        return entity;
    }
}
