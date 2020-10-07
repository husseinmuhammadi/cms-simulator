package com.asan.cms.rpc;

import com.asan.cms.grpc.TransactionServiceGrpc;
import com.asan.cms.rpc.configuration.GrpcEndpointConfiguration;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.asan.cms.grpc.TransactionServiceGrpc.newBlockingStub;

public abstract class GrpcClientBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcClientBase.class);

    @Autowired
    private GrpcEndpointConfiguration endpoint;

    protected TransactionServiceGrpc.TransactionServiceBlockingStub grpcServer() {
        LOGGER.info("Connection to grpc server {}:{}", endpoint.getServerIp(), endpoint.getServerPort());
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(endpoint.getServerIp(), endpoint.getServerPort())
                .usePlaintext()
                .build();

        return newBlockingStub(channel);
    }
}
