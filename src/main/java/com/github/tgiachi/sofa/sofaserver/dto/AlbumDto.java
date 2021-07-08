package com.github.tgiachi.sofa.sofaserver.dto;

import com.github.tgiachi.sofa.sofaserver.dto.base.BaseDto;
import com.github.tgiachi.sofa.sofaserver.entities.TrackEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlbumDto extends BaseDto {

    public ArtistDto artist;

    private String coverUrl;

    private String name;

    private String year;

    public List<TrackDto> tracks = new ArrayList<>();
}
