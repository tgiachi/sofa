package com.github.tgiachi.sofa.sofaserver.rest.base;

import com.github.tgiachi.sofa.sofaserver.data.SofaRestResponse;
import com.github.tgiachi.sofa.sofaserver.data.SofaRestResponseType;
import com.github.tgiachi.sofa.sofaserver.interfaces.dao.IBaseDao;
import com.github.tgiachi.sofa.sofaserver.interfaces.dto.IBaseDto;
import com.github.tgiachi.sofa.sofaserver.interfaces.entities.IBaseEntity;
import com.github.tgiachi.sofa.sofaserver.interfaces.mapper.IDtoMapper;
import com.github.tgiachi.sofa.sofaserver.interfaces.rest.IBaseRestController;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

import java.util.List;

public class AbstractBaseRestController<
        TEntity extends IBaseEntity,
        TDto extends IBaseDto,
        TRepository extends IBaseDao<TEntity, CrudRepository<TEntity, Long>>,
        TMapper extends IDtoMapper<TEntity, TDto>> implements IBaseRestController<TEntity, TDto, TRepository, TMapper> {

    protected TRepository repository;
    protected TMapper mapper;

    public AbstractBaseRestController(TMapper mapper, TRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    @GetMapping("/all")
    public Mono<ResponseEntity<SofaRestResponse<List<TDto>>>> findAll() {
        var response = new SofaRestResponse<List<TDto>>();

        try {
            response.setData(mapper.toDto(repository.findAll()));
            response.setStatus(SofaRestResponseType.SUCCESS);

            return Mono.just(ResponseEntity.ok(response));


        } catch (Exception ex) {
            response.setEx(ex);
            response.setStatus(SofaRestResponseType.ERROR);
            return Mono.just(ResponseEntity.internalServerError().body(response));
        }
    }
}
