package com.asan.cms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Properties;

public class ApplicationPropertiesListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    public static final Logger LOGGER = LoggerFactory.getLogger(ApplicationPropertiesListener.class);

    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
//        Properties properties = new Properties();
//        properties.put("spring.datasource.url", "<my value>");
        environment.getPropertySources().forEach(propertySource -> LOGGER.info("+++{}", propertySource.getName())); //.addFirst(new PropertiesPropertySource("myProps", properties));
    }
}