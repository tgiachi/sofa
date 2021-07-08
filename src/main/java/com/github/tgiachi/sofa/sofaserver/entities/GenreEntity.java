package com.github.tgiachi.sofa.sofaserver.entities;


import com.github.tgiachi.sofa.sofaserver.entities.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "genres", indexes = {@Index(name = "idx_genres_name", columnList = "name")})
@Indexed
public class GenreEntity extends BaseEntity {

    @Field(termVector = TermVector.YES)
    private String name;

    @ManyToMany(mappedBy = "genre", cascade = CascadeType.MERGE)

    private List<TrackEntity> tracks = new ArrayList<>();
}
