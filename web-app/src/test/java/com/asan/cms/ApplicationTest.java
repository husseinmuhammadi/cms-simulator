package com.asan.cms;

import com.asan.cms.web.controller.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

// todo : replace datasource with h2 during test
@SpringBootTest
@ContextConfiguration(classes = HomeController.class)
class ApplicationTest {

    @Test
    void contextLoads() {
    }
}