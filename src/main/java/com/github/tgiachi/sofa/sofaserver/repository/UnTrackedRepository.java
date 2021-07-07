package com.github.tgiachi.sofa.sofaserver.repository;

import com.github.tgiachi.sofa.sofaserver.entities.UnTrackedEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnTrackedRepository extends CrudRepository<UnTrackedEntity, Long> {

    UnTrackedEntity findByHashId(String hash);
}
