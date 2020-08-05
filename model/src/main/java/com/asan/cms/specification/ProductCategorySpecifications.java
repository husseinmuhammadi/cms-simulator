package com.asan.cms.specification;

import com.asan.cms.to.ProductCategory;
import com.asan.cms.to.ProductCategory_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

public class ProductCategorySpecifications extends Specifications {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCategorySpecifications.class);

    public static Specification<ProductCategory> nameStartsWith(String searchTerm) {
        return (productCategory, query, builder) -> builder.like(builder.lower(productCategory.get(ProductCategory_.name)), startWithPattern(searchTerm));
    }

    public static Specification<ProductCategory> nameContains(String searchTerm) {
        return (productCategory, query, builder) -> builder.like(builder.lower(productCategory.get(ProductCategory_.name)), likePattern(searchTerm));
    }
}
