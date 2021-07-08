package com.github.tgiachi.sofa.sofaserver.dao;

import com.github.tgiachi.sofa.sofaserver.dao.base.BaseDao;
import com.github.tgiachi.sofa.sofaserver.entities.PlaylistMasterEntity;
import com.github.tgiachi.sofa.sofaserver.repository.PlaylistMasterRepository;
import org.springframework.stereotype.Component;

@Component
public class PlaylistMasterDao extends BaseDao<PlaylistMasterEntity, PlaylistMasterRepository> {

    public PlaylistMasterDao(PlaylistMasterRepository repository) {
        super(repository);
    }
}
