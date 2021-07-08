package com.github.tgiachi.sofa.sofaserver.repository;

import com.github.tgiachi.sofa.sofaserver.entities.UnTrackedEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UnTrackedRepository extends CrudRepository<UnTrackedEntity, Long> {

    UnTrackedEntity findByHashId(String hash);
}
