package com.github.tgiachi.sofa.sofaserver.mappers;

import com.github.tgiachi.sofa.sofaserver.dto.AlbumDto;
import com.github.tgiachi.sofa.sofaserver.entities.AlbumEntity;
import com.github.tgiachi.sofa.sofaserver.interfaces.mapper.IDtoMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlbumMapper extends IDtoMapper<AlbumEntity, AlbumDto> {
}
