package com.asan.cms.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    com.asan.cms.to.User toUser(com.asan.cms.dto.User user);

    @InheritInverseConfiguration
    com.asan.cms.dto.User fromUser(com.asan.cms.to.User user);
}
