package com.asan.cms.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mapper(componentModel = "spring", uses = {AuditMapper.class})
public interface ProductCategoryIgnoreParentMapper {

    Logger LOGGER = LoggerFactory.getLogger(ProductCategoryIgnoreParentMapper.class);

    ProductCategoryIgnoreParentMapper MAPPER = Mappers.getMapper(ProductCategoryIgnoreParentMapper.class);

    @Mappings({
            @Mapping(target = "masterCategory", ignore = true)
    })
    com.asan.cms.dto.ProductCategory fromProductCategory(com.asan.cms.to.ProductCategory productCategory);

    @InheritInverseConfiguration
    com.asan.cms.to.ProductCategory toProductCategory(com.asan.cms.dto.ProductCategory productCategory);

    // @AfterMapping
    default void addBackReference(@MappingTarget com.asan.cms.dto.ProductCategory target) {
        for (com.asan.cms.dto.ProductCategory productCategory : target.getSubCategories()) {
            productCategory.setMasterCategory(target);
        }
    }
}

