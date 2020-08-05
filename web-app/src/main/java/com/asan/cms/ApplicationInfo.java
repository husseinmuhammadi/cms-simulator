package com.asan.cms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;

public class ApplicationInfo {
    private static final Logger LOGGER = LoggerFactory.getLogger(com.asan.cms.ApplicationInfo.class);
    public static final String APP_INFO = "application-info.properties";

    static final Properties properties= new Properties();
    private static final String GRPC_SERVER_PORT = "grpc.server.port";

    static {
        try (InputStream resource = com.asan.cms.ApplicationInfo.class.getClassLoader().getResourceAsStream(APP_INFO)) {
            properties.load(resource);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static void printInfo() {
        Collections.list((Enumeration<String>) properties.propertyNames()).forEach(key -> {
            LOGGER.info("{} : {}", key, properties.getProperty(key));
        });
    }

    private static Properties getProperties() {
        return properties;
    }

    public static int getGrpcServerPort(){
        return Integer.parseInt((String)properties.get(GRPC_SERVER_PORT));
    }
}
