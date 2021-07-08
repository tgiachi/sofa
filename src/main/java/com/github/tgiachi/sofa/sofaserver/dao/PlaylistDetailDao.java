package com.github.tgiachi.sofa.sofaserver.dao;

import com.github.tgiachi.sofa.sofaserver.dao.base.BaseDao;
import com.github.tgiachi.sofa.sofaserver.entities.PlaylistDetailEntity;
import com.github.tgiachi.sofa.sofaserver.repository.PlaylistDetailRepository;
import org.springframework.stereotype.Component;

@Component
public class PlaylistDetailDao extends BaseDao<PlaylistDetailEntity, PlaylistDetailRepository> {
    public PlaylistDetailDao(PlaylistDetailRepository repository) {
        super(repository);
    }
}
