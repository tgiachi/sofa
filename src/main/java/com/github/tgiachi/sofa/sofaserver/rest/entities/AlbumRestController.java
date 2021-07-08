package com.github.tgiachi.sofa.sofaserver.rest.entities;

import com.github.tgiachi.sofa.sofaserver.dao.AlbumDao;
import com.github.tgiachi.sofa.sofaserver.dto.AlbumDto;
import com.github.tgiachi.sofa.sofaserver.entities.AlbumEntity;
import com.github.tgiachi.sofa.sofaserver.mappers.AlbumMapper;
import com.github.tgiachi.sofa.sofaserver.rest.base.AbstractBaseRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumRestController extends AbstractBaseRestController<AlbumEntity, AlbumDto, AlbumDao, AlbumMapper> {
    public AlbumRestController(AlbumMapper mapper, AlbumDao albumDao) {
        super(mapper, albumDao);
    }
}
