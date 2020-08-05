package com.asan.cms.service;

import com.asan.cms.api.ProductService;
import com.asan.cms.dto.Product;
import com.asan.cms.dto.ProductCategory;
import com.asan.cms.mapper.CycleAvoidingMappingContext;
import com.asan.cms.mapper.ProductCategoryMapper;
import com.asan.cms.mapper.ProductMapper;
import com.asan.cms.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductMapper mapper;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    ProductRepository repository;

    @Override
    public Product save(Product product) {
        LOGGER.info("Saving product");
        return mapper.fromProduct(repository.save(mapper.toProduct(product)));
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(mapper::fromProduct).collect(Collectors.toList());
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        Converter<com.asan.cms.to.Product, com.asan.cms.dto.Product> converter = product -> mapper.fromProduct(product);
        return repository.findAll(pageable).map(converter::convert);
    }

    @Override
    public Page<Product> findAllByCategory(ProductCategory productCategory, Pageable pageable) {
        Converter<com.asan.cms.to.Product, com.asan.cms.dto.Product> converter = product -> mapper.fromProduct(product);
        return repository.findAllByCategory(productCategoryMapper.toProductCategory(productCategory, new CycleAvoidingMappingContext()), pageable).map(converter::convert);
    }

    @Override
    public List<Product> findAllByCategory(ProductCategory productCategory) {
        Converter<com.asan.cms.to.Product, com.asan.cms.dto.Product> converter = product -> mapper.fromProduct(product);
        return repository.findAllByCategory(productCategoryMapper.toProductCategory(productCategory, new CycleAvoidingMappingContext()))
                .stream().map(converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Deleting product {}", id);
        repository.deleteById(id);
    }

    @Override
    public void delete(Product product) {
        repository.delete(mapper.toProduct(product));
    }

    @Override
    public List<Product> getTopProducts() {
        List<com.asan.cms.to.Product> products = repository.findAll();
        LOGGER.info("Number of products: {}", products.size());
        return products.stream().map(mapper::fromProduct).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id).map(mapper::fromProduct);
    }
}
