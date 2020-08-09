package com.asan.cms.rpc;

import com.asan.cms.dto.PaymentRequest;
import com.asan.cms.dto.PaymentResponse;
import com.asan.cms.grpc.TransactionRequest;
import com.asan.cms.grpc.TransactionResponse;
import com.asan.cms.grpc.TransactionServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.asan.cms.grpc.TransactionServiceGrpc.newBlockingStub;

@Component
public class TransactionGrpcServiceImpl extends GrpcTransaction implements TransactionGrpcService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionGrpcServiceImpl.class);


    @Override
    public PaymentResponse doPaymentTransaction(PaymentRequest paymentRequest) {
        LOGGER.info("Payment transaction with gRPC");

        int port = 8080;
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", port)
                .usePlaintext()
                .build();
        TransactionServiceGrpc.TransactionServiceBlockingStub stub = newBlockingStub(channel);
        TransactionRequest request = grpcTransactionGenerator.paymentTransaction(paymentRequest.getCardNo(), paymentRequest.getAmount());
        TransactionResponse response = stub.doFinancialTransaction(request);
        PaymentResponse paymentResponse = new PaymentResponse(response.getStatus(), response.getMessage());
        paymentResponse.setAppliedAmount(response.getAppliedAmount());
        paymentResponse.setRemainedBalance(response.getRemainedBalance());
        paymentResponse.setTranId(response.getTranId());
        paymentResponse.setReferenceTranId(response.getRefTranId());
        return paymentResponse;
    }
}
