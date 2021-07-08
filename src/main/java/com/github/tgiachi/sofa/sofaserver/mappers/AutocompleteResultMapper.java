package com.github.tgiachi.sofa.sofaserver.mappers;

import com.github.tgiachi.sofa.sofaserver.dto.AutocompleteResult;
import com.github.tgiachi.sofa.sofaserver.dto.AutocompleteResultDto;
import com.github.tgiachi.sofa.sofaserver.interfaces.mapper.IDtoMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutocompleteResultMapper extends IDtoMapper<AutocompleteResult, AutocompleteResultDto> {
}
