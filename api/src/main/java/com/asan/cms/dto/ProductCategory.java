package com.asan.cms.dto;

import java.util.HashSet;
import java.util.Set;

public class ProductCategory extends DtoBase {

    public ProductCategory() {
    }

    public ProductCategory(Long id) {
        super(id);
    }

    public ProductCategory(String name) {
        this.name = name;
    }

    private String name;

    private ProductCategory masterCategory;

    private Set<ProductCategory> subCategories = new HashSet<>();

    private Set<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public ProductCategory getMasterCategory() {
        return masterCategory;
    }

    public void setMasterCategory(ProductCategory masterCategory) {
        this.masterCategory = masterCategory;
    }

    public Set<ProductCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<ProductCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
