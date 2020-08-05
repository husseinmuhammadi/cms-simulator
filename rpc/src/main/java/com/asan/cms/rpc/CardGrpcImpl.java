package com.asan.cms.rpc;

import com.asan.cms.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import static com.asan.cms.grpc.TransactionServiceGrpc.newBlockingStub;

@Component
public class CardGrpcImpl extends GrpcTransaction implements CardGrpc {
    public static final Logger LOGGER = LoggerFactory.getLogger(CardGrpcImpl.class);

    @Override
    public int registerCard(String mobileNo, int group) {
        int port = 8080;
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", port)
                .usePlaintext()
                .build();

        TransactionServiceGrpc.TransactionServiceBlockingStub stub = newBlockingStub(channel);

        CardRegisterRequest grpcCardRegisterRequest;
        grpcCardRegisterRequest = grpcTransactionGenerator.grpcCardRegisterTransaction(mobileNo, group);
        LOGGER.info("Request received from client:\n" + grpcCardRegisterRequest);
        CardRegisterResponse grpcCardRegisterResponse = stub.registerCard(grpcCardRegisterRequest);
        byte[] utf8 = grpcCardRegisterResponse.getMsg().getBytes(StandardCharsets.UTF_8);
        LOGGER.info("Response from server: ");
        LOGGER.info("status: {}", grpcCardRegisterResponse.getStatus());
        LOGGER.info("error message: {}", new String(utf8));

        return grpcCardRegisterResponse.getStatus();
    }

    @Override
    public int getCardInfo(String mobileNo, int group) {
        int port = 8080;
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", port)
                .usePlaintext()
                .build();

        TransactionServiceGrpc.TransactionServiceBlockingStub stub = newBlockingStub(channel);
        CardInfoRequest grpcCardInfoRequest = grpcTransactionGenerator.grpcCardInfoTransaction(mobileNo, group);
        LOGGER.info("Request received from client:\n" + grpcCardInfoRequest);
        CardInfoResponse grpcCardInfoResponse = stub.getCardInfo(grpcCardInfoRequest);
        byte[] utf8 = grpcCardInfoResponse.getMsg().getBytes(StandardCharsets.UTF_8);
        LOGGER.info("Response from server: ");
        LOGGER.info("status: {}", grpcCardInfoResponse.getStatus());
        LOGGER.info("error message: {}", new String(utf8));
        return grpcCardInfoResponse.getStatus();
    }

    public void fundTransfer() {
        int port = 8080;
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", port)
                .usePlaintext()
                .build();

        TransactionServiceGrpc.TransactionServiceBlockingStub stub = newBlockingStub(channel);

        FundTransferRequest fundTransferRequest = grpcTransactionGenerator.fundTransferTransaction("9832551217745378");
        LOGGER.info("Request received from client:\n" + fundTransferRequest);
        TransactionResponse statementResponse = stub.doFundTransfer(fundTransferRequest);
        LOGGER.info("Response received from server:\n" + statementResponse);
        LOGGER.info("Response {status: {}, message: {}}", statementResponse.getStatus(), statementResponse.getMessage());
    }
}
