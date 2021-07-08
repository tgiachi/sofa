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
@Table(name = "artists", indexes = {@Index(name = "idx_artist_name", columnList = "name")})
@Indexed
public class ArtistEntity extends BaseEntity {

    @Column(length = 200)
    @Field(termVector = TermVector.YES)
    private String name;

    @OneToMany(mappedBy = "artist")
    private List<AlbumEntity> albums = new ArrayList<>();


}
