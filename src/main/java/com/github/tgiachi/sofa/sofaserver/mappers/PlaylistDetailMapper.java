package com.github.tgiachi.sofa.sofaserver.mappers;

import com.github.tgiachi.sofa.sofaserver.dto.PlaylistDetailDto;
import com.github.tgiachi.sofa.sofaserver.entities.PlaylistDetailEntity;
import com.github.tgiachi.sofa.sofaserver.interfaces.mapper.IDtoMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaylistDetailMapper extends IDtoMapper<PlaylistDetailEntity, PlaylistDetailDto> {
}
