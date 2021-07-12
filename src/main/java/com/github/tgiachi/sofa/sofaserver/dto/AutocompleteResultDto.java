package com.github.tgiachi.sofa.sofaserver.dto;

import com.github.tgiachi.sofa.sofaserver.dto.base.BaseDto;
import lombok.*;

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
