package com.asan.cms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProfileManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileManager.class);

    @Autowired
    Environment environment;

    public List<String> getActiveProfiles() {
        return Arrays.asList(environment.getActiveProfiles());
    }

    @EventListener
    public void printActiveProfiles(ApplicationReadyEvent event) {
        getActiveProfiles().forEach(profile -> LOGGER.info("Active profile: {}", profile));
    }
}
