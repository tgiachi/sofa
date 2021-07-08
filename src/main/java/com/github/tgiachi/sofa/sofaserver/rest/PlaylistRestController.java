package com.github.tgiachi.sofa.sofaserver.rest;

import com.github.tgiachi.sofa.sofaserver.services.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/playlists")
public class PlaylistRestController {

    private final PlaylistService playlistService;

    public PlaylistRestController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/random")
    public ResponseEntity<String> createRandomPlaylist(@RequestParam String name, @RequestParam int size) {

        playlistService.createRandomPlaylist(name, size);
        return ResponseEntity.ok("");
    }

}
