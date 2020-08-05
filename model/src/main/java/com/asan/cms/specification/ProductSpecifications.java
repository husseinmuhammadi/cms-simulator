package com.asan.cms.specification;

import com.asan.cms.to.Product;
import com.asan.cms.to.Product_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications extends Specifications {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductSpecifications.class);

    public static Specification<Product> priceIsBetween(Double min, Double max) {
        return (Specification<Product>) (product, query, builder) -> builder.between(product.get(Product_.amount), min, max);
    }

    public static Specification<Product> nameStartsWith(String searchTerm) {
        return (Specification<Product>) (product, query, builder) -> builder.like(builder.lower(product.get(Product_.name)), startWithPattern(searchTerm));
    }
}
