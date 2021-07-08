package com.github.tgiachi.sofa.sofaserver.dto;

import com.github.tgiachi.sofa.sofaserver.dto.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlbumDto extends BaseDto {

    public ArtistDto artist;

    private String coverUrl;

    private String name;

    private String year;
}
