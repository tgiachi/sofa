package com.github.tgiachi.sofa.sofaserver.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "albums")
public class AlbumEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "album_id")
    public ArtistEntity artist;

    @Column(length = 500)
    private String coverUrl;

    private String name;

    private String year;

    @OneToMany(mappedBy = "album")
    public List<TrackEntity> tracks = new ArrayList<>();
}
