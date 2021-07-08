package com.github.tgiachi.sofa.sofaserver.entities.base;

import com.github.tgiachi.sofa.sofaserver.interfaces.entities.IBaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity implements IBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;

    @Column(length = 200)
    private String hashId;

    @Version
    private long version;
}
