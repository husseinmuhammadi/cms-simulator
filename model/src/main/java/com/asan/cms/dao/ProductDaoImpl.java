package com.asan.cms.dao;

import com.asan.cms.to.Product;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class ProductDaoImpl implements ProductDao {

    @Autowired
    EntityManager entityManager;

    @Override
    public void remove(Product product) {
        entityManager.remove(product);
    }
}
