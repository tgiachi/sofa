package com.github.tgiachi.sofa.sofaserver.entities;


import com.github.tgiachi.sofa.sofaserver.entities.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "genres")
public class GenreEntity extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy = "genre", cascade = CascadeType.MERGE)
    private List<TrackEntity> tracks = new ArrayList<>();
}
