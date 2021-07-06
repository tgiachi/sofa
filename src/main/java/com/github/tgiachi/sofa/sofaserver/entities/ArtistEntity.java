package com.github.tgiachi.sofa.sofaserver.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "artists")
public class ArtistEntity extends BaseEntity {

    @Column(length = 200)
    private String name;


}
