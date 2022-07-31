package com.innowise.cadenseapp.service;

import com.innowise.cadenseapp.repo.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public abstract class CommonServiceImpl<E, R extends CommonRepository<E>> implements CommonService<E> {
    protected final R repo;

    @PersistenceContext
    private EntityManager entityManger;

    @Autowired
    public CommonServiceImpl(R repo) {
        this.repo = repo;
    }

    @Override
    public E findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<E> findAll() {
        return repo.findAll();
    }

    @Override
    @Transactional
    public E save(E entity) {
        entityManger.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public E update(E entity) {
        return entityManger.merge(entity);
    }
}
