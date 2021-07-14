package com.github.tgiachi.sofa.sofaserver.dto;

import com.github.tgiachi.sofa.sofaserver.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDto extends BaseDto {
    private String name;

    private String coverUrl;
}
