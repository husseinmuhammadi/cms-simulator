package com.asan.cms.rpc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("stage")
@PropertySource("classpath:grpc-configuration-stage.properties")
public class StageGrpcEndpointConfiguration extends GrpcEndpointConfiguration {
}
