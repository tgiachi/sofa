package com.github.tgiachi.sofa.sofaserver.rest.entities;

import com.github.tgiachi.sofa.sofaserver.dao.PlaylistMasterDao;
import com.github.tgiachi.sofa.sofaserver.dto.PlaylistMasterDto;
import com.github.tgiachi.sofa.sofaserver.data.SofaRestResponse;
import com.github.tgiachi.sofa.sofaserver.data.SofaRestResponseType;
import com.github.tgiachi.sofa.sofaserver.entities.PlaylistMasterEntity;
import com.github.tgiachi.sofa.sofaserver.mappers.PlaylistMasterMapper;
import com.github.tgiachi.sofa.sofaserver.rest.base.AbstractBaseRestController;
import com.github.tgiachi.sofa.sofaserver.services.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/playlists")
public class PlaylistRestController extends AbstractBaseRestController<PlaylistMasterEntity, PlaylistMasterDto, PlaylistMasterDao, PlaylistMasterMapper> {
    private final PlaylistService playlistService;

    public PlaylistRestController(PlaylistMasterMapper mapper, PlaylistMasterDao dao, PlaylistService playlistService) {
        super(mapper, dao);
        this.playlistService = playlistService;
    }

    @GetMapping("/random")
    public ResponseEntity<SofaRestResponse<Object>> createRandomPlaylist(@RequestParam String name, @RequestParam int size, @RequestParam(defaultValue = "1") int playlistNumber) {


        playlistService.createRandomPlaylist(name, size, playlistNumber);

        return ResponseEntity.ok(SofaRestResponse.builder().status(SofaRestResponseType.SUCCESS).data("ok").build());
    }

}
