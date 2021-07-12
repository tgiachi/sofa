package com.github.tgiachi.sofa.sofaserver.dto;

import com.github.tgiachi.sofa.sofaserver.dto.PlaylistDetailDto;
import com.github.tgiachi.sofa.sofaserver.dto.base.BaseDto;
import com.github.tgiachi.sofa.sofaserver.entities.PlaylistDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistMasterDto extends BaseDto {


    private String name;

    private String coverUrl;

    private List<PlaylistDetailDto> tracks = new ArrayList<>();
}
