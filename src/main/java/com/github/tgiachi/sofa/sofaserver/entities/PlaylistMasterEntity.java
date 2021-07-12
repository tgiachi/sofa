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
@Table(name = "playlists", indexes = {@Index(name = "idx_playlist_name", columnList = "name")})
@Indexed
public class PlaylistMasterEntity extends BaseEntity {

    @Column(length = 1000)
    @Field(termVector = TermVector.YES)
    private String name;

    @Column(length = 300)
    private String coverUrl;

    @OneToMany(mappedBy = "playlist", fetch = FetchType.EAGER)
    private List<PlaylistDetailEntity> tracks = new ArrayList<>();
}
