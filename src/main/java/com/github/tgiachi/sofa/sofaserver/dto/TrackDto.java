package com.github.tgiachi.sofa.sofaserver.dto;

import com.github.tgiachi.sofa.sofaserver.dto.base.BaseDto;
import com.github.tgiachi.sofa.sofaserver.entities.AlbumEntity;
import com.github.tgiachi.sofa.sofaserver.entities.GenreEntity;
import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackDto extends BaseDto {
    private String filename;

    private int trackOrder;

    private long fileSize;

    private String trackName;

    private long trackLength;

    private int bitrate;

    private long playCount = 0;
    
    private List<GenreDto> genre = new ArrayList<>();

}
