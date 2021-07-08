package com.github.tgiachi.sofa.sofaserver.interfaces.dao;

import com.github.tgiachi.sofa.sofaserver.interfaces.entities.IBaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IBaseDao<TEntity extends IBaseEntity, TRepository extends CrudRepository<TEntity, Long>> {
    List<TEntity> findAll();
}
