package com.github.tgiachi.sofa.sofaserver.interfaces.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IBaseEntity extends Serializable {
    long getId();

    void setId(long id);

    LocalDateTime getCreatedDateTime();

    void setCreatedDateTime(LocalDateTime value);

    LocalDateTime getUpdatedDateTime();

    void setUpdatedDateTime(LocalDateTime value);

    String getHashId();

    void setHashId(String value);

    String getMDbId();

    void setMDbId(String value);

}
