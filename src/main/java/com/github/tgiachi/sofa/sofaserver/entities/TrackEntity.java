package com.github.tgiachi.sofa.sofaserver.entities;

import com.github.tgiachi.sofa.sofaserver.entities.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tracks", indexes = {@Index(columnList = "trackName", name = "idx_trackName")})
@Indexed
public class TrackEntity extends BaseEntity {

    @Column(length = 1000)
    @Field(termVector = TermVector.YES)
    private String filename;

    private int trackOrder;

    private long fileSize;

    @Analyzer
    @Field(termVector = TermVector.YES)
    private String trackName;

    private long trackLength;

    private int bitrate;

    private long playCount = 0;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "genre_tracks",
            joinColumns = {@JoinColumn(name = "genre_id")},
            inverseJoinColumns = {@JoinColumn(name = "track_id")}
    )

    @LazyCollection(LazyCollectionOption.FALSE)
    private List<GenreEntity> genre = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "album_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private AlbumEntity album;
}
