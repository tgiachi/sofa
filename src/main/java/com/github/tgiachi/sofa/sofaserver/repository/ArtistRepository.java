package com.github.tgiachi.sofa.sofaserver.repository;

import com.github.tgiachi.sofa.sofaserver.entities.ArtistEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends CrudRepository<ArtistEntity, Long> {

    ArtistEntity findByName(String name);

}
