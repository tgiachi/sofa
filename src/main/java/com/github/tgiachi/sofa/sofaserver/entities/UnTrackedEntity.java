package com.github.tgiachi.sofa.sofaserver.entities;

import com.github.tgiachi.sofa.sofaserver.entities.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "untrack_entities")

public class UnTrackedEntity extends BaseEntity {

    @Column(length = 1000)
    private String filename;

    private long fileSize;

    private String fileHandler;
}
