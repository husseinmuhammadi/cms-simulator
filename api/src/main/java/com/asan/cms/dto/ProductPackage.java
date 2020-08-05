package com.asan.cms.dto;

import java.util.List;

public class ProductPackage {
    private final List<Product> products;

    public ProductPackage(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
