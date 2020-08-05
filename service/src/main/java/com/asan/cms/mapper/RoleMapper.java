package com.asan.cms.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    com.asan.cms.to.Role toRole(com.asan.cms.dto.Role role);

    @InheritInverseConfiguration
    com.asan.cms.dto.Role fromRole(com.asan.cms.to.Role role);
}
