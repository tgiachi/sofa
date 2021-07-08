package com.github.tgiachi.sofa.sofaserver.entities;

import com.github.tgiachi.sofa.sofaserver.entities.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "playlists")
public class PlaylistMasterEntity extends BaseEntity {

    @Column(length = 1000)
    private String name;

    @Column(length = 300)
    private String coverUrl;

    @OneToMany(mappedBy = "playlist")
    private List<PlaylistDetailEntity> tracks = new ArrayList<>();
}
