package com.asan.cms.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import javax.persistence.MappedSuperclass;

@Mapper(componentModel = "spring", uses = {AuditMapper.class})
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "name", target = "productName")
    @Mapping(source = "code", target = "productCode")
    com.asan.cms.dto.Product fromProduct(com.asan.cms.to.Product product);

    @InheritInverseConfiguration
    com.asan.cms.to.Product toProduct(com.asan.cms.dto.Product product);
}
