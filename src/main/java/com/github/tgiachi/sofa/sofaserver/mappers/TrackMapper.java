package com.github.tgiachi.sofa.sofaserver.mappers;

import com.github.tgiachi.sofa.sofaserver.dto.TrackDto;
import com.github.tgiachi.sofa.sofaserver.entities.TrackEntity;
import com.github.tgiachi.sofa.sofaserver.interfaces.mapper.IDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper(componentModel = "spring")
@Transactional
public interface TrackMapper extends IDtoMapper<TrackEntity, TrackDto> {


    TrackDto toDto(TrackEntity entity);

    List<TrackDto> toDto(List<TrackEntity> entities);

}
