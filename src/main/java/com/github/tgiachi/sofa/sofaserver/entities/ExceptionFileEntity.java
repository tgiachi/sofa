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
@Table(name = "exceptions_files")
public class ExceptionFileEntity extends BaseEntity {

    @Column(length = 1024)
    private String fileName;

    private String exception;
}
