package com.github.tgiachi.sofa.sofaserver.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDateTime createdDateTime;

    private LocalDateTime updateDataTime;


    @Column(length = 200)
    private String hashId;

    @Version
    private long version;
}
