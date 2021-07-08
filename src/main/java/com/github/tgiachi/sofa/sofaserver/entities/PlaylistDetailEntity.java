package com.github.tgiachi.sofa.sofaserver.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "playlist_tracks")
public class PlaylistDetailEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    public PlaylistMasterEntity playlist;

    @ManyToOne
    @JoinColumn(name = "track_id")
    public TrackEntity trackEntity;

    private int trackOrder;
}
