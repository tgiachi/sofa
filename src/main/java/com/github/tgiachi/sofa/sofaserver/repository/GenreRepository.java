package com.github.tgiachi.sofa.sofaserver.repository;

import com.github.tgiachi.sofa.sofaserver.entities.ArtistEntity;
import com.github.tgiachi.sofa.sofaserver.entities.GenreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface GenreRepository extends CrudRepository<GenreEntity, Long> {
    GenreEntity findByName(String name);
    GenreEntity getByHashId(String hashId);
}
