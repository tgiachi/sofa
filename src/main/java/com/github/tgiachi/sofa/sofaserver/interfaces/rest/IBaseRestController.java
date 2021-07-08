package com.github.tgiachi.sofa.sofaserver.interfaces.rest;

import com.github.tgiachi.sofa.sofaserver.data.SofaRestResponse;
import com.github.tgiachi.sofa.sofaserver.interfaces.dao.IBaseDao;
import com.github.tgiachi.sofa.sofaserver.interfaces.dto.IBaseDto;
import com.github.tgiachi.sofa.sofaserver.interfaces.entities.IBaseEntity;
import com.github.tgiachi.sofa.sofaserver.interfaces.mapper.IDtoMapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IBaseRestController<TEntity extends IBaseEntity,
        TDto extends IBaseDto,
        TRepository extends IBaseDao<TEntity, CrudRepository<TEntity, Long>>,
        TMapper extends IDtoMapper<TEntity, TDto>> {

    Mono<ResponseEntity<SofaRestResponse<List<TDto>>>> findAll();

    Mono<ResponseEntity<SofaRestResponse<TDto>>> findByHashId(String hashId);

}
