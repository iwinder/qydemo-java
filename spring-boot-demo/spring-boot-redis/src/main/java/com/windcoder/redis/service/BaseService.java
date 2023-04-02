package com.windcoder.redis.service;


import com.windcoder.redis.repository.SupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;


public class BaseService<T, ID extends Serializable, R extends SupportRepository<T, ID>> {
    private Class<T> clazz;
    protected R repository;

    public BaseService() {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.clazz = clazz;
    }

    @Autowired
    public <S extends R> void setRepository(S repository) {
        this.repository = repository;
    }


    public <S extends T> S save(S entity) {
        return repository.saveAndFlush(entity);
    }

    public Optional<T> findOne(ID id) {
        return repository.findById(id);
    }

    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable){

        return repository.findAll(Example.of(example.getProbe()), pageable);
    }

    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }


//    public void delete(List<ID> ids) {
//        for (ID id : ids) {
//            delete(id, false);
//        }
//    }


//    public void delete(ID[] ids) {
//        delete(Arrays.asList(ids), false);
//    }
//
//
//    public void delete(ID id, boolean isPhysical) {
//        if (!checkDeleteable(id)) {
//            return;
//        }
//
//        if (isReparable && !isPhysical) {
//            tagDelete(id);
//        } else {
//            repository.delete(id);
//        }
//    }
}
