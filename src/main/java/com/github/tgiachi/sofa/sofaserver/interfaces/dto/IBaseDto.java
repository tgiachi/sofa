package com.github.tgiachi.sofa.sofaserver.interfaces.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IBaseDto extends Serializable {
    long getId();

    void setId(long id);

//    LocalDateTime getCreatedDateTime();
//
//    void setCreatedDateTime(LocalDateTime value);
//
//    LocalDateTime getUpdatedDateTime();
//
//    void setUpdatedDateTime(LocalDateTime value);

    String getHashId();

    void setHashId(String value);
}
