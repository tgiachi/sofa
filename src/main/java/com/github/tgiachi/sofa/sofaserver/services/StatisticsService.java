package com.github.tgiachi.sofa.sofaserver.services;

import com.github.tgiachi.sofa.sofaserver.dao.ArtistDao;
import com.github.tgiachi.sofa.sofaserver.dao.PlaylistMasterDao;
import com.github.tgiachi.sofa.sofaserver.dao.TrackDao;
import com.github.tgiachi.sofa.sofaserver.dto.StatisticsDto;
import com.github.tgiachi.sofa.sofaserver.entities.TrackEntity;
import com.github.tgiachi.sofa.sofaserver.services.base.BaseService;
import org.springframework.stereotype.Service;

import static humanize.Humanize.binaryPrefix;
import static humanize.Humanize.duration;

@Service
public class StatisticsService extends BaseService {

    private final ArtistDao artistDao;
    private final TrackDao trackDao;
    private final PlaylistMasterDao playlistMasterDao;

    public StatisticsService(ArtistDao artistDao, TrackDao trackDao, PlaylistMasterDao playlistMasterDao) {
        this.artistDao = artistDao;
        this.trackDao = trackDao;
        this.playlistMasterDao = playlistMasterDao;
    }

    public StatisticsDto calculateStats() {

        var totalSize = trackDao.findAll().stream().map(TrackEntity::getFileSize).reduce(0L, Long::sum);
        var totalLength = trackDao.findAll().stream().map(TrackEntity::getTrackLength).reduce(0L, Long::sum);


        return StatisticsDto.builder()
                .totalArtists(artistDao.count())
                .totalPlaylists(playlistMasterDao.count())
                .totalTracks(trackDao.count())
                .totalFileSize(binaryPrefix(totalSize))
                .totalAudioLength(duration(totalLength))
                .build();

    }
}
