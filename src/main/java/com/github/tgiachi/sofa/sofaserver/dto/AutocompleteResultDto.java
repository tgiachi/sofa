package com.github.tgiachi.sofa.sofaserver.dto;

import com.github.tgiachi.sofa.sofaserver.dao.PlaylistMasterDto;
import com.github.tgiachi.sofa.sofaserver.dto.base.BaseDto;
import com.github.tgiachi.sofa.sofaserver.entities.AlbumEntity;
import com.github.tgiachi.sofa.sofaserver.entities.ArtistEntity;
import com.github.tgiachi.sofa.sofaserver.entities.PlaylistMasterEntity;
import com.github.tgiachi.sofa.sofaserver.entities.TrackEntity;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutocompleteResultDto extends BaseDto {
    List<TrackDto> tracks = new ArrayList<>();
    List<AlbumDto> albums = new ArrayList<>();
    List<ArtistDto> artists = new ArrayList<>();
    List<PlaylistMasterDto> playlists = new ArrayList<>();
}
