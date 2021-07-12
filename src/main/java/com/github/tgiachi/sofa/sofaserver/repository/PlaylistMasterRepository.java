package com.github.tgiachi.sofa.sofaserver.repository;


import com.github.tgiachi.sofa.sofaserver.entities.PlaylistMasterEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PlaylistMasterRepository extends CrudRepository<PlaylistMasterEntity, Long> {
    PlaylistMasterEntity getByHashId(String hashId);

}
