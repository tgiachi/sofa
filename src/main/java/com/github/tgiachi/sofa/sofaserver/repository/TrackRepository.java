package com.github.tgiachi.sofa.sofaserver.repository;

import com.github.tgiachi.sofa.sofaserver.entities.AlbumEntity;
import com.github.tgiachi.sofa.sofaserver.entities.TrackEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TrackRepository extends CrudRepository<TrackEntity, Long> {

    TrackEntity findByAlbumAndTrackName(AlbumEntity albumEntity, String trackName);

    List<TrackEntity> findByTrackNameContaining(String track);

    TrackEntity findByHashId(String hashId);

    @Query("SELECT sum(fileSize) from TrackEntity")
    long sumByFileSize();

    @Query("select sum(trackLength) from TrackEntity ")
    long sumByTrackLength();


    @Query("select t.id from TrackEntity t ")
    List<Long> selectAllTracksId();
}
