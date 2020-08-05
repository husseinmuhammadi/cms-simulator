package com.asan.cms.repository;

import com.asan.cms.to.Product;
import com.asan.cms.to.Product_;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static com.asan.cms.specification.ProductSpecifications.nameStartsWith;
import static com.asan.cms.specification.ProductSpecifications.priceIsBetween;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositorySpecificationIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositorySpecificationIntegrationTest.class);

    @Autowired
    EntityManager entityManager;

    @Autowired
    ProductRepository productRepository;

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
    void whenSearchForCheapPencil_thenItShouldBeFound() {
        List<Product> products = productRepository.findAll((product, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.like(builder.lower(product.get(Product_.name)), "Pen%".toLowerCase()));
            predicates.add(builder.between(product.get(Product_.amount), 50.0, 100.0));
            return builder.and(predicates.toArray(new Predicate[0]));
        });

        products.stream().map(p -> String.format("id:%d, name:%s, price: %-10.2f", p.getId(), p.getName(), p.getAmount())).forEach(LOGGER::info);
        assertThat(products).extracting(product -> product.getName().toLowerCase()).containsAnyOf("pencil");
    }

    @Test
    void whenSearchForExpensivePen_thenItShouldBeFound() {
        List<Product> products = productRepository.findAll(
                nameStartsWith("Pen").and(priceIsBetween(3000.0, 8000.0))
        );

        products.stream().map(p -> String.format("id:%d, name:%s, price: %-10.2f", p.getId(), p.getName(), p.getAmount())).forEach(LOGGER::info);
        assertThat(products).extracting(product -> product.getName().toLowerCase()).containsAnyOf("pen");
    }
}
