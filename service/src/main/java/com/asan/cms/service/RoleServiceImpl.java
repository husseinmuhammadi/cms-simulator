package com.asan.cms.service;

import com.asan.cms.api.RoleService;
import com.asan.cms.dto.Role;
import com.asan.cms.mapper.RoleMapper;
import com.asan.cms.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImplBase<com.asan.cms.to.Role> implements RoleService {

    public static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper mapper;

    @Autowired
    RoleRepository repository;

    protected RoleServiceImpl() {
        super(com.asan.cms.to.Role.class);
    }

    @Override
    JpaRepository getRepository() {
        return repository;
    }

    @Override
    public Role findByName(String name) {
        return mapper.fromRole(repository.findByName(name));
    }

    @Override
    public boolean exists(String name){
        return repository.existsByName(name);
    }
}
