package com.asan.cms.rpc;

import com.asan.cms.dto.CardIssueResponse;
import com.asan.cms.dto.CardStatusInquiryResponse;
import com.asan.cms.grpc.*;
import com.asan.cms.rpc.configuration.GrpcEndpointConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CardGrpcImpl implements CardGrpc {

    public static final Logger LOGGER = LoggerFactory.getLogger(CardGrpcImpl.class);

    @Autowired
    GrpcEndpointConfiguration endpoint;

    @Autowired
    GrpcTransactionGenerator grpcTransactionGenerator;

    @Override
    public CardIssueResponse registerCard(String mobileNo, int group) {
        LOGGER.info("Issue a new card based on mobile number: {}, card group: {}", mobileNo, group);

        return new GrpcServer<CardRegisterRequest, CardRegisterResponse>(endpoint) {
            @Override
            CardRegisterRequest getGrpcRequest() {
                return grpcTransactionGenerator.grpcCardRegisterTransaction(mobileNo, group);
            }

            @Override
            Function<CardRegisterRequest, CardRegisterResponse> getGrpcMethod(CMSServiceGrpc.CMSServiceBlockingStub stub) {
                return stub::registerCard;
            }
        }.call(grpcResponse -> {
            CardIssueResponse response = new CardIssueResponse();
            response.setStatus(grpcResponse.getStatus());
            response.setMessage(grpcResponse.getMsg());
            response.setCardNo(grpcResponse.getCardNo());
            return response;
        });
    }

    @Override
    public CardStatusInquiryResponse inquiryStatus(String mobileNo, int group) {
        LOGGER.info("Inquiry card status for given mobile number: {}, card group: {}", mobileNo, group);

        return new GrpcServer<CardInfoRequest, CardInfoResponse>(endpoint) {
            @Override
            CardInfoRequest getGrpcRequest() {
                return grpcTransactionGenerator.grpcCardInfoTransaction(mobileNo, group);
            }

            @Override
            Function<CardInfoRequest, CardInfoResponse> getGrpcMethod(CMSServiceGrpc.CMSServiceBlockingStub stub) {
                return stub::getCardInfo;
            }
        }.call(grpcResponse -> {
            CardStatusInquiryResponse response = new CardStatusInquiryResponse();
            response.setStatus(grpcResponse.getStatus());
            response.setMessage(grpcResponse.getMsg());
            response.setCardNo(grpcResponse.getCardNo());
            response.setCardStatus(grpcResponse.getCardStat());
            return response;
        });
    }
}
