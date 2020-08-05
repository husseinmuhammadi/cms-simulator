package com.asan.cms.repository;

import com.asan.cms.to.Role;
import com.asan.cms.to.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryIntegrationTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryIntegrationTest.class);

    @Autowired
    UserRepository userRepository;

    @Test
    void saveUserWithRoles() {
        User admin = new User("John");
        admin.getRoles().add(new Role("USER"));
        userRepository.save(admin);

        // when
        User user = userRepository.findByUsername("John");

        // then
        Assertions.assertThat(user.getRoles()).isNotEmpty();
        Assertions.assertThat(user.getRoles()).extracting(Role::getName).contains("USER");
    }
}