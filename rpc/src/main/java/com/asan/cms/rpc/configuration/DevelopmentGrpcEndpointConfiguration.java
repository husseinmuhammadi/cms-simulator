package com.asan.cms.rpc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("dev")
@PropertySource("classpath:grpc-configuration-dev.properties")
public class DevelopmentGrpcEndpointConfiguration extends GrpcEndpointConfiguration {
}
