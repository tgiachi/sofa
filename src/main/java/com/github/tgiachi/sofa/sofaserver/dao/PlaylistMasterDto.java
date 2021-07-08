package com.github.tgiachi.sofa.sofaserver.dao;

import com.github.tgiachi.sofa.sofaserver.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.Column;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistMasterDto extends BaseDto {


    private String name;

    private String coverUrl;
}
