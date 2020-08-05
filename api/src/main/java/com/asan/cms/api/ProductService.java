package com.asan.cms.api;

import com.asan.cms.dto.Product;
import com.asan.cms.dto.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(Product product);

    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByCategory(ProductCategory productCategory, Pageable pageable);

    List<Product> findAllByCategory(ProductCategory productCategory);

    void delete(Long id);

    void delete(Product product);

    List<Product> getTopProducts();

    Optional<Product> findById(Long id);
}
