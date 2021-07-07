package com.github.tgiachi.sofa.sofaserver.repository;

import com.github.tgiachi.sofa.sofaserver.entities.GenreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<GenreEntity, Long> {
    GenreEntity findByName(String name);

}
