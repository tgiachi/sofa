package com.github.tgiachi.sofa.sofaserver.interfaces.mapper;

import com.github.tgiachi.sofa.sofaserver.interfaces.dto.IBaseDto;
import com.github.tgiachi.sofa.sofaserver.interfaces.entities.IBaseEntity;

import java.util.List;

public interface IDtoMapper<TEntity extends IBaseEntity, TDto extends IBaseDto> {
    TDto toDto(TEntity entity);

    List<TDto> toDto(List<TEntity> entities);

    TEntity toEntity(TDto dto);

    List<TEntity> toEntity(List<TDto> dtos);

}
