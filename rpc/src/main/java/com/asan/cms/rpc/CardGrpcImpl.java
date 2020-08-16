package com.asan.cms.rpc;

import com.asan.cms.dto.CardIssueResponse;
import com.asan.cms.dto.CardStatusInquiryResponse;
import com.asan.cms.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import static com.asan.cms.grpc.TransactionServiceGrpc.newBlockingStub;

@Component
public class CardGrpcImpl implements CardGrpc {
    public static final Logger LOGGER = LoggerFactory.getLogger(CardGrpcImpl.class);

    @Autowired
    GrpcTransactionGenerator grpcTransactionGenerator;

    @Override
    public CardIssueResponse registerCard(String mobileNo, int group) {
        int port = 8080;
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", port)
                .usePlaintext()
                .build();

        TransactionServiceGrpc.TransactionServiceBlockingStub stub = newBlockingStub(channel);

        CardRegisterRequest grpcCardRegisterRequest;
        grpcCardRegisterRequest = grpcTransactionGenerator.grpcCardRegisterTransaction(mobileNo, group);
        LOGGER.info("Request received from client:\n" + grpcCardRegisterRequest);
        CardRegisterResponse grpcCardRegisterResponse = stub.registerCard(grpcCardRegisterRequest);
        CardIssueResponse response = new CardIssueResponse();
        response.setStatus(grpcCardRegisterResponse.getStatus());
        response.setMessage(grpcCardRegisterResponse.getMsg());
        response.setCardNo(grpcCardRegisterResponse.getCardNo());
        LOGGER.info("Response from server: ");
        LOGGER.info("status: {}", grpcCardRegisterResponse.getStatus());
        return response;
    }

    @Override
    public CardStatusInquiryResponse inquiryStatus(String mobileNo, int group) {
        int port = 8080;
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", port)
                .usePlaintext()
                .build();

        TransactionServiceGrpc.TransactionServiceBlockingStub stub = newBlockingStub(channel);
        CardInfoRequest grpcCardInfoRequest = grpcTransactionGenerator.grpcCardInfoTransaction(mobileNo, group);
        CardInfoResponse grpcCardInfoResponse = stub.getCardInfo(grpcCardInfoRequest);
        byte[] utf8 = grpcCardInfoResponse.getMsg().getBytes(StandardCharsets.UTF_8);
        CardStatusInquiryResponse response = new CardStatusInquiryResponse();
        response.setStatus(grpcCardInfoResponse.getStatus());
        response.setMessage(grpcCardInfoResponse.getMsg());
        response.setCardNo(grpcCardInfoResponse.getCardNo());
        response.setCardStatus(grpcCardInfoResponse.getCardStat());
        return response;
    }
}
