package com.github.tgiachi.sofa.sofaserver.services.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

public abstract class BaseService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void onStart() {

    }
}
