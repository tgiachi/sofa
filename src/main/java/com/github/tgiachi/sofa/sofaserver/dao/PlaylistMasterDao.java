package com.github.tgiachi.sofa.sofaserver.dao;

import com.github.tgiachi.sofa.sofaserver.dao.base.BaseDao;
import com.github.tgiachi.sofa.sofaserver.entities.PlaylistMasterEntity;
import com.github.tgiachi.sofa.sofaserver.interfaces.dao.IBaseDao;
import com.github.tgiachi.sofa.sofaserver.repository.PlaylistMasterRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public class PlaylistMasterDao extends BaseDao<PlaylistMasterEntity, PlaylistMasterRepository> implements IBaseDao<PlaylistMasterEntity, CrudRepository<PlaylistMasterEntity, Long>> {

    public PlaylistMasterDao(PlaylistMasterRepository repository) {
        super(repository);
    }

    @Override
    public PlaylistMasterEntity findByHashId(String hashId) {
        return repository.getByHashId(hashId);
    }
}
