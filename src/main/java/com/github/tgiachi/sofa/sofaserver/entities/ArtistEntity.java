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
@Table(name = "artists")
public class ArtistEntity extends BaseEntity {

    @Column(length = 200)
    private String name;

    @OneToMany(mappedBy = "artist")
    private List<AlbumEntity> albums = new ArrayList<>();


}
