package com.asan.cms.repository;

import com.asan.cms.to.Product;
import com.asan.cms.to.base.Printable;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

/**
 * Using junit 4 needed to uncomment @RunWith(SpringRunner.class)
 * Otherwise productRepository will not be injected
 */
//@RunWith(SpringRunner.class)
@DataJpaTest
// @AutoConfigureTestDatabase(replace = ANY)
class ProductRepositoryIntegrationTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryIntegrationTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void init() {
        // Add cheap pencil
        Product pencil = new Product();
        pencil.setName("Pencil");
        pencil.setAmount(68.0);
        productRepository.save(pencil);

        // Add expensive pen
        Product pen = new Product();
        pen.setName("Pen");
        pen.setAmount(4800.0);
        productRepository.save(pen);

        LOGGER.info("Products saved successfully!");
    }

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Product needle = new Product();
        needle.setName("Needle");
        entityManager.persist(needle);
        entityManager.flush();

        // when
        Product found = productRepository.findByName(needle.getName());

        // then
        Assertions.assertThat(found.getName())
                .isEqualTo(needle.getName());
    }

    @Test
    public void saveMicroCannula() {
        Product microCannula = new Product();
        microCannula.setName("Micro Cannula");
        entityManager.persist(microCannula);
        entityManager.flush();

        // when
        Product found = productRepository.findByName(microCannula.getName());
        LOGGER.info(">>>> {} {}", found.getId(), found.getName());
    }

    @Test
    public void allProducts() {
        List<Product> products = productRepository.findAll();
        LOGGER.info(">>>> Products count: {}", products.size());
    }

    @Test
    void listAllProducts() {
        Product product1 = new Product();
        product1.setName("Test01");
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Test01");
        productRepository.save(product2);

        LOGGER.info("----------------------------------------");
        List<Product> products = productRepository.findAll();
        products.stream().map(Printable::format).forEach(LOGGER::info);

        LOGGER.info("----------------------------------------");
        productRepository.remove(product1);
        products = productRepository.findAll();
        products.stream().map(Printable::format).forEach(LOGGER::info);
    }

    @Test
    void whenProductPencilPersist_thenItShouldExist() {
        ExampleMatcher modelMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("name", ignoreCase());

        Product probe = new Product();
        probe.setName("pencil");
        Example<Product> example = Example.of(probe, modelMatcher);
        boolean exists = productRepository.exists(example);

        Assertions.assertThat(exists).isEqualTo(true);
    }
}