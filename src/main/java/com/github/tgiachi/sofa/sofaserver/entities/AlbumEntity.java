package com.github.tgiachi.sofa.sofaserver.entities;

import com.github.tgiachi.sofa.sofaserver.entities.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "albums", indexes = {@Index(name = "idx_album_name", columnList = "name")})
@Indexed
public class AlbumEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "artist_id")
    public ArtistEntity artist;

    @Column(length = 500)
    private String coverUrl;

    @Field(termVector = TermVector.YES)
    private String name;

    private String year;

    private long globalPlayCount = 0;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "album")
    public List<TrackEntity> tracks = new ArrayList<>();
}
