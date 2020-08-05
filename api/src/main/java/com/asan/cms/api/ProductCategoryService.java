package com.asan.cms.api;

import com.asan.cms.dto.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryService {
    ProductCategory save(ProductCategory productCategory);

    List<ProductCategory> findAll();

    List<ProductCategory> findRoot();

    Page<ProductCategory> findAll(Pageable pageable);

    void delete(Long id);

    void delete(ProductCategory productCategory);

    Optional<ProductCategory> findById(Long id);

    Optional<ProductCategory> findByName(String name);
}
