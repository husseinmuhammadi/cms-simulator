package com.asan.cms.repository;

import com.asan.cms.to.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class ProductCategoryRepositoryIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCategoryRepositoryIntegrationTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @BeforeEach
    public void init() {
        LOGGER.info("Add pharmaceutical product category ...");
        ProductCategory productCategory = productCategoryRepository.save(new ProductCategory("pharmaceutical"));
        LOGGER.info("Pharmaceutical added successfully. id: {}", productCategory.getId());
    }

    @Test
    public void whenItemsSaveUnderGel_thenGelShouldConsistAllThatItems() {
        // given
        ProductCategory gel = new ProductCategory("Gel");
        gel.getSubCategories().add(new ProductCategory("Gel1"));
        gel.getSubCategories().add(new ProductCategory("Gel2"));
        entityManager.persist(gel);
        entityManager.flush();

        // when
        /*
        List<ProductCategory> found = productCategoryRepository.findByName(gel.getName());

        found.forEach(category -> {
            LOGGER.info(category.getName());
            if (category.getSubCategories() != null) {
                category.getSubCategories().forEach(pc -> LOGGER.info(pc.getName()));
            }
        });
        */

        Optional<ProductCategory> found = productCategoryRepository.findByName(gel.getName());

        found.ifPresent(
                productCategory -> {
                    LOGGER.info(productCategory.getName());
                    if (productCategory.getSubCategories() != null) {
                        productCategory.getSubCategories().forEach(subCategory -> LOGGER.info(subCategory.getName()));
                    }
                }
        );

        // then
//        assertThat(found.getName())
//                .isEqualTo(needle.getName());
    }

    @Test
    void searchProductCategoryByExample() {
        LOGGER.info("Search for product category");
        EntityManager em = entityManager.getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ProductCategory> query = builder.createQuery(ProductCategory.class);
        Root<ProductCategory> productCategory = query.from(ProductCategory.class);
        query.where(builder.like(productCategory.get("name"), "%pharmaceutical%"));
        List<ProductCategory> productCategories = em.createQuery(query).getResultList();
        productCategories.stream().map(ProductCategory::getName).forEach(name -> LOGGER.info("product category: {}", name));
    }

    void getEntityTypeFromMetamodel() {
        EntityManager em = entityManager.getEntityManager();
        Metamodel metamodel = em.getMetamodel();
        EntityType<ProductCategory> ProductCategory_ = metamodel.entity(ProductCategory.class);
    }
}

