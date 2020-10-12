package com.asan.cms.rpc.configuration;

import org.springframework.beans.factory.annotation.Value;

public abstract class GrpcEndpointConfiguration {

    @Value("${endpoint.server.ip}")
    String serverIp;

    @Value("${endpoint.server.port}")
    int serverPort;

    public String getServerIp() {
        return serverIp;
    }

    public int getServerPort() {
        return serverPort;
    }
}
