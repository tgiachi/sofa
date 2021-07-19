package com.github.tgiachi.sofa.sofaserver.repository;

import com.github.tgiachi.sofa.sofaserver.entities.DirectoryWatchEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DirectoryWatchRepository extends CrudRepository<DirectoryWatchEntity, Long> {

    DirectoryWatchEntity findByDirectory(String name);
}
