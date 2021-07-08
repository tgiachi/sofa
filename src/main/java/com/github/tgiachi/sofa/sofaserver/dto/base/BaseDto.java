package com.github.tgiachi.sofa.sofaserver.dto.base;

import com.github.tgiachi.sofa.sofaserver.interfaces.dto.IBaseDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDto implements IBaseDto {

    private long id;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;

    private String hashId;

    private long version;
}
