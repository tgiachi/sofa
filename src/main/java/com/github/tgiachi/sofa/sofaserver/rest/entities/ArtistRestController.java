package com.github.tgiachi.sofa.sofaserver.rest.entities;

import com.github.tgiachi.sofa.sofaserver.dao.ArtistDao;
import com.github.tgiachi.sofa.sofaserver.dto.ArtistDto;
import com.github.tgiachi.sofa.sofaserver.entities.ArtistEntity;
import com.github.tgiachi.sofa.sofaserver.mappers.ArtistMapper;
import com.github.tgiachi.sofa.sofaserver.rest.base.AbstractBaseRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/artists")
public class ArtistRestController extends AbstractBaseRestController<ArtistEntity, ArtistDto, ArtistDao, ArtistMapper> {


    public ArtistRestController(ArtistMapper mapper, ArtistDao artistDao) {
        super(mapper, artistDao);
    }
}
