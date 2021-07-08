package com.github.tgiachi.sofa.sofaserver.services;

import com.github.tgiachi.sofa.sofaserver.dao.PlaylistDetailDao;
import com.github.tgiachi.sofa.sofaserver.dao.PlaylistMasterDao;
import com.github.tgiachi.sofa.sofaserver.entities.PlaylistDetailEntity;
import com.github.tgiachi.sofa.sofaserver.entities.PlaylistMasterEntity;
import com.github.tgiachi.sofa.sofaserver.repository.TrackRepository;
import com.github.tgiachi.sofa.sofaserver.services.base.BaseService;
import com.github.tgiachi.sofa.sofaserver.utils.Md5Utils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PlaylistService extends BaseService {

    private final TrackRepository trackRepository;
    private final PlaylistMasterDao playlistMasterRepository;
    private final PlaylistDetailDao playlistDetailRepository;

    public PlaylistService(TrackRepository trackRepository, PlaylistMasterDao playlistMasterRepository, PlaylistDetailDao playlistDetailRepository) {
        this.trackRepository = trackRepository;
        this.playlistMasterRepository = playlistMasterRepository;
        this.playlistDetailRepository = playlistDetailRepository;
    }


    public void createRandomPlaylist(String name, int size) {
        var playlistMaster = new PlaylistMasterEntity();
        playlistMaster.setName(name);
        playlistMaster.setHashId(Md5Utils.md5(name));
        playlistMaster.setCoverUrl("https://source.unsplash.com/random");

        var allTracks = StreamSupport.stream(trackRepository.findAll().spliterator(), true)
                .collect(Collectors.toList());

        playlistMaster = playlistMasterRepository.insert(playlistMaster);

        for (int i = 0; i < size; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, allTracks.size());
            var trackEntity = allTracks.get(randomNum);
            var playListItem = new PlaylistDetailEntity();
            playListItem.setTrackOrder(i + 1);
            playListItem.setCreatedDateTime(LocalDateTime.now());
            playListItem.setUpdateDataTime(LocalDateTime.now());
            playListItem.setTrackEntity(trackEntity);
            playListItem.setPlaylist(playlistMaster);
            playListItem.setHashId(Md5Utils.md5(trackEntity.getTrackName()));
            playlistDetailRepository.insert(playListItem);
        }
    }
}
