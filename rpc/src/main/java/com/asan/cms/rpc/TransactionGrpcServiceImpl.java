package com.asan.cms.rpc;

import com.asan.cms.dto.*;
import com.asan.cms.grpc.TransactionResponse;
import com.asan.cms.grpc.TransactionServiceGrpc;
import com.asan.cms.rpc.configuration.GrpcEndpointConfiguration;
import com.asan.cms.type.TransactionProcessTypeEnum;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.asan.cms.grpc.TransactionServiceGrpc.newBlockingStub;

@Component
public class TransactionGrpcServiceImpl implements TransactionGrpcService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionGrpcServiceImpl.class);

    @Autowired
    GrpcTransactionGenerator grpcTransactionGenerator;
    
    @Autowired
    GrpcEndpointConfiguration endpoint;

    @Override
    public PaymentResponse doPaymentTransaction(PaymentRequest paymentRequest) {
        LOGGER.info("Payment transaction with gRPC");
        
        ManagedChannel channel = ManagedChannelBuilder.forAddress(endpoint.getServerIp(), endpoint.getServerPort())
                .usePlaintext()
                .build();
        TransactionServiceGrpc.TransactionServiceBlockingStub stub = newBlockingStub(channel);

        TransactionResponse response = stub.doFinancialTransaction(
                grpcTransactionGenerator.paymentTransaction(paymentRequest.getCardNo(), paymentRequest.getAmount(), paymentRequest.getGateway(), paymentRequest.getService(), paymentRequest.getReferenceTransactionId(), paymentRequest.getHost(), paymentRequest.getRrn())
        );
        PaymentResponse paymentResponse = new PaymentResponse(response.getStatus(), response.getMessage());
        paymentResponse.setAppliedAmount(response.getAppliedAmount());
        paymentResponse.setRemainedBalance(response.getRemainedBalance());
        paymentResponse.setTranId(response.getTranId());
        paymentResponse.setReferenceTranId(response.getRefTranId());
        return paymentResponse;
    }

    @Override
    public DepositResponse doDepositTransaction(DepositRequest depositRequest) {
        LOGGER.info("Deposit transaction with gRPC");

        
        ManagedChannel channel = ManagedChannelBuilder.forAddress(endpoint.getServerIp(), endpoint.getServerPort())
                .usePlaintext()
                .build();
        TransactionServiceGrpc.TransactionServiceBlockingStub stub = newBlockingStub(channel);

        TransactionResponse response = stub.doFinancialTransaction(
                grpcTransactionGenerator.depositTransaction(depositRequest.getCardNo(), depositRequest.getAmount(), depositRequest.getGateway(), depositRequest.getService(), depositRequest.getReferenceTransactionId(), depositRequest.getHost(), depositRequest.getRrn())
        );
        DepositResponse depositResponse = new DepositResponse(response.getStatus(), response.getMessage());
        depositResponse.setAppliedAmount(response.getAppliedAmount());
        depositResponse.setRemainedBalance(response.getRemainedBalance());
        depositResponse.setTranId(response.getTranId());
        depositResponse.setReferenceTranId(response.getRefTranId());
        return depositResponse;
    }

    @Override
    public CashoutResponse doCashoutTransaction(CashoutRequest cashoutRequest) {
        LOGGER.info("Cashout transaction with gRPC");

        ManagedChannel channel = ManagedChannelBuilder.forAddress(endpoint.getServerIp(), endpoint.getServerPort())
                .usePlaintext()
                .build();
        TransactionServiceGrpc.TransactionServiceBlockingStub stub = newBlockingStub(channel);

        TransactionResponse response = stub.doFinancialTransaction(
                grpcTransactionGenerator.cashoutTransaction(cashoutRequest.getCardNo(), cashoutRequest.getAmount(), cashoutRequest.getGateway(), cashoutRequest.getService(), cashoutRequest.getReferenceTransactionId(), cashoutRequest.getHost(), cashoutRequest.getRrn())
        );
        CashoutResponse cashoutResponse = new CashoutResponse(response.getStatus(), response.getMessage());
        cashoutResponse.setAppliedAmount(response.getAppliedAmount());
        cashoutResponse.setRemainedBalance(response.getRemainedBalance());
        cashoutResponse.setTranId(response.getTranId());
        cashoutResponse.setReferenceTranId(response.getRefTranId());
        return cashoutResponse;
    }

    @Override
    public PurchaseResponse doPurchaseTransaction(PurchaseRequest purchaseRequest) {
        LOGGER.info("Purchase transaction with gRPC");

        
        ManagedChannel channel = ManagedChannelBuilder.forAddress(endpoint.getServerIp(), endpoint.getServerPort())
                .usePlaintext()
                .build();
        TransactionServiceGrpc.TransactionServiceBlockingStub stub = newBlockingStub(channel);

        TransactionResponse response = stub.doFinancialTransaction(
                grpcTransactionGenerator.purchaseTransaction(purchaseRequest.getCardNo(), purchaseRequest.getAmount(), purchaseRequest.getGateway(), purchaseRequest.getService(), purchaseRequest.getReferenceTransactionId(), purchaseRequest.getHost(), purchaseRequest.getRrn())
        );
        PurchaseResponse purchaseResponse = new PurchaseResponse(response.getStatus(), response.getMessage());
        purchaseResponse.setAppliedAmount(response.getAppliedAmount());
        purchaseResponse.setRemainedBalance(response.getRemainedBalance());
        purchaseResponse.setTranId(response.getTranId());
        purchaseResponse.setReferenceTranId(response.getRefTranId());
        return purchaseResponse;
    }

    @Override
    public BalanceInquiryResponse doBalanceInquiryTransaction(BalanceInquiryRequest balanceInquiryRequest) {
        LOGGER.info("BalanceInquiry transaction with gRPC");

        ManagedChannel channel = ManagedChannelBuilder.forAddress(endpoint.getServerIp(), endpoint.getServerPort())
                .usePlaintext()
                .build();
        TransactionServiceGrpc.TransactionServiceBlockingStub stub = newBlockingStub(channel);

        TransactionResponse response = stub.doBalanceInquiry(
                grpcTransactionGenerator.balanceInquiryTransaction(balanceInquiryRequest.getCardNo(), balanceInquiryRequest.getGateway(), balanceInquiryRequest.getService(), balanceInquiryRequest.getReferenceTransactionId(), balanceInquiryRequest.getHost())
        );
        BalanceInquiryResponse balanceInquiryResponse = new BalanceInquiryResponse(response.getStatus(), response.getMessage());
        balanceInquiryResponse.setAppliedAmount(response.getAppliedAmount());
        balanceInquiryResponse.setRemainedBalance(response.getRemainedBalance());
        balanceInquiryResponse.setTranId(response.getTranId());
        balanceInquiryResponse.setReferenceTranId(response.getRefTranId());
        return balanceInquiryResponse;
    }

    @Override
    public StatementResponse doStatementTransaction(StatementRequest statementRequest) {
        LOGGER.info("Statement transaction with gRPC");

        
        ManagedChannel channel = ManagedChannelBuilder.forAddress(endpoint.getServerIp(), endpoint.getServerPort())
                .usePlaintext()
                .build();
        TransactionServiceGrpc.TransactionServiceBlockingStub stub = newBlockingStub(channel);

        TransactionResponse response = stub.doStatement(
                grpcTransactionGenerator.statementTransaction(statementRequest.getCardNo())
        );
        StatementResponse statementResponse = new StatementResponse(response.getStatus(), response.getMessage());
        statementResponse.setAppliedAmount(response.getAppliedAmount());
        statementResponse.setRemainedBalance(response.getRemainedBalance());
        statementResponse.setTranId(response.getTranId());
        statementResponse.setReferenceTranId(response.getRefTranId());
        return statementResponse;
    }

    @Override
    public FundTransferResponse doFundTransferTransaction(FundTransferRequest fundTransferRequest) {
        
        ManagedChannel channel = ManagedChannelBuilder.forAddress(endpoint.getServerIp(), endpoint.getServerPort())
                .usePlaintext()
                .build();

        TransactionServiceGrpc.TransactionServiceBlockingStub stub = newBlockingStub(channel);

        TransactionResponse response = stub.doFundTransfer(
                grpcTransactionGenerator.fundTransferTransaction(fundTransferRequest.getSourceCard(), fundTransferRequest.getDestinationCard(), fundTransferRequest.getAmount(), fundTransferRequest.getGateway(), fundTransferRequest.getService(), fundTransferRequest.getReferenceTransactionId(), fundTransferRequest.getHost())
        );
        FundTransferResponse fundTransferResponse = new FundTransferResponse(response.getStatus(), response.getMessage());
        fundTransferResponse.setAppliedAmount(response.getAppliedAmount());
        fundTransferResponse.setRemainedBalance(response.getRemainedBalance());
        fundTransferResponse.setTranId(response.getTranId());
        fundTransferResponse.setReferenceTranId(response.getRefTranId());
        return fundTransferResponse;
    }

    @Override
    public TransactionInquiryResponse doInquiryTransaction(TransactionRequest transactionRequest) {
        
        ManagedChannel channel = ManagedChannelBuilder.forAddress(endpoint.getServerIp(), endpoint.getServerPort())
                .usePlaintext()
                .build();

        TransactionServiceGrpc.TransactionServiceBlockingStub stub = newBlockingStub(channel);

        com.asan.cms.grpc.TransactionInquiryResponse grpcTransactionInquiryResponse = stub.inquiryTransaction(
                grpcTransactionGenerator.inquiryTransaction(transactionRequest.getGateway(), transactionRequest.getService(), transactionRequest.getReferenceTransactionId(), transactionRequest.getHost())
        );
        TransactionInquiryResponse transactionInquiryResponse = new TransactionInquiryResponse();
        transactionInquiryResponse.setStatus(grpcTransactionInquiryResponse.getStatus());
        transactionInquiryResponse.setTransactionStatus(grpcTransactionInquiryResponse.getTranStatus());
        transactionInquiryResponse.setRrn(grpcTransactionInquiryResponse.getRrn());
        transactionInquiryResponse.setAmount(grpcTransactionInquiryResponse.getAmount());
        transactionInquiryResponse.setBalance(grpcTransactionInquiryResponse.getBalance());

        return transactionInquiryResponse;
    }

    @Override
    public ReversalResponse doReverseTransaction(FinancialRequest financialRequest) {
        
        ManagedChannel channel = ManagedChannelBuilder.forAddress(endpoint.getServerIp(), endpoint.getServerPort())
                .usePlaintext()
                .build();

        TransactionServiceGrpc.TransactionServiceBlockingStub stub = newBlockingStub(channel);
        com.asan.cms.grpc.TransactionRequest grpcFinancialRequest = grpcTransactionGenerator.financialTransaction(
                TransactionProcessTypeEnum.valueOf(financialRequest.getProcessingCode()),
                financialRequest.getCardNo(),
                financialRequest.getAmount(),
                financialRequest.getGateway(),
                financialRequest.getService(),
                financialRequest.getReferenceTransactionId(),
                financialRequest.getHost(),
                financialRequest.getRrn()
        );

        TransactionResponse grpcReversalResponse = stub.doTransactionReverse(
                grpcTransactionGenerator.reversalTransaction(grpcFinancialRequest)
        );
        ReversalResponse reversalResponse = new ReversalResponse(grpcReversalResponse.getStatus(), grpcReversalResponse.getMessage());
        reversalResponse.setAppliedAmount(grpcReversalResponse.getAppliedAmount());
        reversalResponse.setRemainedBalance(grpcReversalResponse.getRemainedBalance());
        reversalResponse.setTranId(grpcReversalResponse.getTranId());
        reversalResponse.setReferenceTranId(grpcReversalResponse.getRefTranId());
        return reversalResponse;
    }
}
