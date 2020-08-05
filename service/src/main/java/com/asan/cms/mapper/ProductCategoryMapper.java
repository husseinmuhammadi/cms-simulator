package com.asan.cms.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mapper(componentModel = "spring", uses = {AuditMapper.class})
public interface ProductCategoryMapper {

    ProductCategoryMapper MAPPER = Mappers.getMapper(ProductCategoryMapper.class);

    Logger LOGGER = LoggerFactory.getLogger(ProductCategoryMapper.class);

//    @Mappings({
//            @Mapping(target = "masterCategory", ignore = true)
//    })
    com.asan.cms.dto.ProductCategory fromProductCategory(com.asan.cms.to.ProductCategory productCategory, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    com.asan.cms.to.ProductCategory toProductCategory(com.asan.cms.dto.ProductCategory productCategory, @Context CycleAvoidingMappingContext context);

    // @AfterMapping
    default void addBackReference(@MappingTarget com.asan.cms.dto.ProductCategory target) {
        for (com.asan.cms.dto.ProductCategory productCategory : target.getSubCategories()) {
            productCategory.setMasterCategory(target);
        }
    }
}

