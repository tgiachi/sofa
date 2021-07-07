package com.github.tgiachi.sofa.sofaserver.repository;

import com.github.tgiachi.sofa.sofaserver.entities.AlbumEntity;
import com.github.tgiachi.sofa.sofaserver.entities.TrackEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends CrudRepository<TrackEntity, Long> {

    TrackEntity findByAlbumAndTrackName(AlbumEntity albumEntity, String trackName);

    List<TrackEntity> findByTrackNameContaining(String track);

    TrackEntity findByHashId(String hashId);
}
