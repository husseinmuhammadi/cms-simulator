package com.asan.cms.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;

@Component
public class ResourceFiltering {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceFiltering.class);

    public static final String APP_INFO = "project-info.properties";

    static final Properties properties = new Properties();

    static {
        try (InputStream resource = ResourceFiltering.class.getClassLoader().getResourceAsStream(APP_INFO)) {
            properties.load(resource);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @EventListener
    public static void printConfiguration(ApplicationReadyEvent event) {
        LOGGER.info("RESOURCE FILTERING");
        Collections.list((Enumeration<String>) properties.propertyNames()).forEach(key -> {
            LOGGER.info("{} : {}", key, properties.getProperty(key));
        });
    }
}
