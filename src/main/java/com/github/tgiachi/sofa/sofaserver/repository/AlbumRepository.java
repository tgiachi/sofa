package com.github.tgiachi.sofa.sofaserver.repository;

import com.github.tgiachi.sofa.sofaserver.entities.AlbumEntity;
import com.github.tgiachi.sofa.sofaserver.entities.ArtistEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AlbumRepository extends CrudRepository<AlbumEntity, Long> {
    AlbumEntity findByArtistAndName(ArtistEntity artistEntity, String name);

    List<AlbumEntity> getByHashId(String hashId);

    List<AlbumEntity> findAlbumEntitiesByArtist(ArtistEntity artistEntity);

}
