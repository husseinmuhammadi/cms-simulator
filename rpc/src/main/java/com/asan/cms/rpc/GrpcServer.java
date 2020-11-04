package com.asan.cms.rpc;

import com.asan.cms.grpc.CMSServiceGrpc;
import com.asan.cms.rpc.configuration.GrpcEndpointConfiguration;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class GrpcServer<T, R> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcServer.class);

    private final GrpcEndpointConfiguration endpoint;

    protected GrpcServer(GrpcEndpointConfiguration endpoint) {
        this.endpoint = endpoint;
    }

    abstract T getGrpcRequest();

    abstract Function<T, R> getGrpcMethod(CMSServiceGrpc.CMSServiceBlockingStub stub);

    public R call() {
        LOGGER.info("Grpc call {}:{}", endpoint.getServerIp(), endpoint.getServerPort());
        LOGGER.info("Grpc request: {}", getGrpcRequest().toString());
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(endpoint.getServerIp(), endpoint.getServerPort())
                .usePlaintext()
                .build();

        CMSServiceGrpc.CMSServiceBlockingStub stub = CMSServiceGrpc.newBlockingStub(channel);
        R grpcResponse = getGrpcMethod(stub).apply(getGrpcRequest());
        LOGGER.info("Grpc response {}", grpcResponse.toString());
        channel.shutdown();
        return grpcResponse;
    }

    public void call(Consumer<R> converter) {
        converter.accept(call());
    }

    public <R1> R1 call(Function<R, R1> converter) {
        return converter.apply(call());
    }
}
