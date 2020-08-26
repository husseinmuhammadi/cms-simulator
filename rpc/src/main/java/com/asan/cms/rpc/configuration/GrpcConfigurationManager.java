package com.asan.cms.rpc.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class GrpcConfigurationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcConfigurationManager.class);

    @Autowired
    GrpcEndpointConfiguration grpcEndpointConfiguration;

    @EventListener
    public void printGrpcConfiguration(ApplicationReadyEvent event) {
        LOGGER.info("Grpc endpoint server ip: {}", grpcEndpointConfiguration.getServerIp());
        LOGGER.info("Grpc endpoint server port: {}", grpcEndpointConfiguration.getServerPort());
    }

}
