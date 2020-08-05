package com.asan.cms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class ServiceImplBase<T> {

    abstract JpaRepository getRepository();

    private final Class<T> entityType;

    protected ServiceImplBase(Class<T> entityType) {
        this.entityType = entityType;
    }

    public Page findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }
}
