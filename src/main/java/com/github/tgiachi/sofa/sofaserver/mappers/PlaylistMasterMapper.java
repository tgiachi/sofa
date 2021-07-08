package com.github.tgiachi.sofa.sofaserver.mappers;

import com.github.tgiachi.sofa.sofaserver.dao.PlaylistMasterDto;
import com.github.tgiachi.sofa.sofaserver.entities.PlaylistMasterEntity;
import com.github.tgiachi.sofa.sofaserver.interfaces.mapper.IDtoMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaylistMasterMapper extends IDtoMapper<PlaylistMasterEntity, PlaylistMasterDto> {
}
