package com.github.tgiachi.sofa.sofaserver.dto;

import com.github.tgiachi.sofa.sofaserver.entities.AlbumEntity;
import com.github.tgiachi.sofa.sofaserver.entities.ArtistEntity;
import com.github.tgiachi.sofa.sofaserver.entities.PlaylistMasterEntity;
import com.github.tgiachi.sofa.sofaserver.entities.TrackEntity;
import com.github.tgiachi.sofa.sofaserver.entities.base.BaseEntity;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutocompleteResult extends BaseEntity {
    List<TrackEntity> tracks = new ArrayList<>();
    List<AlbumEntity> albums = new ArrayList<>();
    List<ArtistEntity> artists = new ArrayList<>();
    List<PlaylistMasterEntity> playlists = new ArrayList<>();

}
