package com.github.tgiachi.sofa.sofaserver.dao;

import com.github.tgiachi.sofa.sofaserver.dao.base.BaseDao;
import com.github.tgiachi.sofa.sofaserver.entities.UnTrackedEntity;
import com.github.tgiachi.sofa.sofaserver.repository.UnTrackedRepository;
import org.springframework.stereotype.Component;

@Component
public class UnTrackedDao extends BaseDao<UnTrackedEntity, UnTrackedRepository> {
    public UnTrackedDao(UnTrackedRepository repository) {
        super(repository);
    }
}
