package com.asan.cms.repository;

import com.asan.cms.to.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductCategoryRepositoryCriteriaTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCategoryRepositoryCriteriaTest.class);

    @Autowired
    EntityManager entityManager;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @BeforeEach
    void init() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("Vital");
        entityManager.persist(productCategory);
        LOGGER.info("Product category saved successfully!");
    }

    @Test
    void whenProductCategoryPersistInEntityManager_thenAtLeastOneProductCategoryIsExists() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductCategory> query = builder.createQuery(ProductCategory.class);
        Root<ProductCategory> from = query.from(ProductCategory.class);
        CriteriaQuery<ProductCategory> select = query.select(from);
        select.orderBy(builder.asc(from.get("name")));
        TypedQuery<ProductCategory> typedQuery = entityManager.createQuery(select);
        List<ProductCategory> productCategories = typedQuery.getResultList();
        LOGGER.info("--> {}", productCategories.size());
        assertThat(productCategories.size()).isGreaterThan(0);
    }
}
