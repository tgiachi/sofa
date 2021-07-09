package com.github.tgiachi.sofa.sofaserver.dto;

import com.github.tgiachi.sofa.sofaserver.entities.AlbumEntity;
import com.github.tgiachi.sofa.sofaserver.entities.ArtistEntity;
import com.github.tgiachi.sofa.sofaserver.entities.PlaylistMasterEntity;
import com.github.tgiachi.sofa.sofaserver.entities.TrackEntity;
import com.github.tgiachi.sofa.sofaserver.entities.base.BaseEntity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor

@Builder
public class AutocompleteResult extends BaseEntity {
    List<TrackEntity> tracks = new ArrayList<>();
    List<AlbumEntity> albums = new ArrayList<>();
    List<ArtistEntity> artists = new ArrayList<>();
    List<PlaylistMasterEntity> playlists = new ArrayList<>();

    public AutocompleteResult() {
        setUpdatedDateTime(LocalDateTime.now());
        setCreatedDateTime(LocalDateTime.now());
        setHashId(UUID.randomUUID().toString().replace("-", ""));
    }
}
