package com.github.tgiachi.sofa.sofaserver.entities;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "genres")
public class GenreEntity extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "genre")
    private List<TrackEntity> tracks = new ArrayList<>();
}
