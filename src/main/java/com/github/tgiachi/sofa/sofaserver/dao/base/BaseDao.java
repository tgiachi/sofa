package com.github.tgiachi.sofa.sofaserver.dao.base;

import com.github.tgiachi.sofa.sofaserver.interfaces.entities.IBaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class BaseDao<TEntity extends IBaseEntity, TRepository extends CrudRepository<TEntity, Long>> {

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
            entity.setUpdatedDateTime(LocalDateTime.now());

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
            entity.setUpdatedDateTime(LocalDateTime.now());

            repository.save(entity);
            semaphore.release();
            return entity;
        } catch (Exception ex) {
            semaphore.release();
            logger.error("Error during insert: ", ex);
        }
        return entity;
    }

    public List<TEntity> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), true)
                .collect(Collectors.toList());
    }

    public long count() {
        return repository.count();
    }
}
