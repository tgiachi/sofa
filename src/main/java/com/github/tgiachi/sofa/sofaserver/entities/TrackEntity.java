package com.github.tgiachi.sofa.sofaserver.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private AlbumEntity album;
}
