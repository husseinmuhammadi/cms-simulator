package com.asan.cms.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuditMapper {

    AuditMapper MAPPER = Mappers.getMapper(AuditMapper.class);

    com.asan.cms.to.base.Audit toAudit(com.asan.cms.dto.base.Audit audit);

    @InheritInverseConfiguration
    com.asan.cms.dto.base.Audit fromAudit(com.asan.cms.to.base.Audit audit);
}
