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
@Table(name = "tracks", indexes = {@Index(columnList = "trackName", name = "idx_trackName")})
public class TrackEntity extends BaseEntity {

    @Column(length = 1000)
    private String filename;

    private int trackOrder;

    private long fileSize;

    private String trackName;

    private long trackLength;

    private int bitrate;

    private long playCount = 0;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "genre_tracks",
            joinColumns = {@JoinColumn(name = "genre_id")},
            inverseJoinColumns = {@JoinColumn(name = "track_id")}
    )

    private List<GenreEntity> genre = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "album_id")
    private AlbumEntity album;
}
