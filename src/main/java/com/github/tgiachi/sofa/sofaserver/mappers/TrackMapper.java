package com.github.tgiachi.sofa.sofaserver.mappers;

import com.github.tgiachi.sofa.sofaserver.dto.TrackDto;
import com.github.tgiachi.sofa.sofaserver.entities.TrackEntity;
import com.github.tgiachi.sofa.sofaserver.interfaces.mapper.IDtoMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrackMapper extends IDtoMapper<TrackEntity, TrackDto> {
}
