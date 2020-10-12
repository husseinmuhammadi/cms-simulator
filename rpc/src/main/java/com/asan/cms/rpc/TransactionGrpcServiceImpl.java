package com.asan.cms.rpc;

import com.asan.cms.dto.*;
import com.asan.cms.grpc.TransactionResponse;
import com.asan.cms.grpc.TransactionServiceGrpc;
import com.asan.cms.rpc.configuration.GrpcEndpointConfiguration;
import com.asan.cms.type.TransactionProcessTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TransactionGrpcServiceImpl implements TransactionGrpcService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionGrpcServiceImpl.class);

    @Autowired
    GrpcEndpointConfiguration endpoint;

    @Autowired
    GrpcTransactionGenerator grpcTransactionGenerator;

    @Override
    public PaymentResponse doPaymentTransaction(PaymentRequest paymentRequest) {
        LOGGER.info("Payment transaction with gRPC");

        return new GrpcServer<com.asan.cms.grpc.TransactionRequest, TransactionResponse>(endpoint) {
            @Override
            com.asan.cms.grpc.TransactionRequest getGrpcRequest() {
                return grpcTransactionGenerator.paymentTransaction(
                        paymentRequest.getCardNo(),
                        paymentRequest.getAmount(),
                        paymentRequest.getGateway(),
                        paymentRequest.getService(),
                        paymentRequest.getReferenceTransactionId(),
                        paymentRequest.getHost(),
                        paymentRequest.getRrn());
            }

            @Override
            Function<com.asan.cms.grpc.TransactionRequest, TransactionResponse> getGrpcMethod(TransactionServiceGrpc.TransactionServiceBlockingStub stub) {
                return stub::doFinancialTransaction;
            }
        }.call(grpcResponse -> {
            PaymentResponse paymentResponse = new PaymentResponse(grpcResponse.getStatus(), grpcResponse.getMessage());
            paymentResponse.setAppliedAmount(grpcResponse.getAppliedAmount());
            paymentResponse.setRemainedBalance(grpcResponse.getRemainedBalance());
            paymentResponse.setTranId(grpcResponse.getTranId());
            paymentResponse.setReferenceTranId(grpcResponse.getRefTranId());
            return paymentResponse;
        });
    }

    @Override
    public DepositResponse doDepositTransaction(DepositRequest depositRequest) {
        LOGGER.info("Deposit transaction with gRPC");

        return new GrpcServer<com.asan.cms.grpc.TransactionRequest, TransactionResponse>(endpoint) {
            @Override
            com.asan.cms.grpc.TransactionRequest getGrpcRequest() {
                return grpcTransactionGenerator.depositTransaction(
                        depositRequest.getCardNo(),
                        depositRequest.getAmount(),
                        depositRequest.getGateway(),
                        depositRequest.getService(),
                        depositRequest.getReferenceTransactionId(),
                        depositRequest.getHost(),
                        depositRequest.getRrn()
                );
            }

            @Override
            Function<com.asan.cms.grpc.TransactionRequest, TransactionResponse> getGrpcMethod(TransactionServiceGrpc.TransactionServiceBlockingStub stub) {
                return stub::doFinancialTransaction;
            }
        }.call(grpcResponse -> {
            DepositResponse depositResponse = new DepositResponse(grpcResponse.getStatus(), grpcResponse.getMessage());
            depositResponse.setAppliedAmount(grpcResponse.getAppliedAmount());
            depositResponse.setRemainedBalance(grpcResponse.getRemainedBalance());
            depositResponse.setTranId(grpcResponse.getTranId());
            depositResponse.setReferenceTranId(grpcResponse.getRefTranId());
            return depositResponse;
        });
    }

    @Override
    public CashoutResponse doCashoutTransaction(CashoutRequest cashoutRequest) {
        LOGGER.info("Cashout transaction with gRPC");

        return new GrpcServer<com.asan.cms.grpc.TransactionRequest, TransactionResponse>(endpoint) {
            @Override
            com.asan.cms.grpc.TransactionRequest getGrpcRequest() {
                return grpcTransactionGenerator.cashoutTransaction(
                        cashoutRequest.getCardNo(),
                        cashoutRequest.getAmount(),
                        cashoutRequest.getGateway(),
                        cashoutRequest.getService(),
                        cashoutRequest.getReferenceTransactionId(),
                        cashoutRequest.getHost(),
                        cashoutRequest.getRrn()
                );
            }

            @Override
            Function<com.asan.cms.grpc.TransactionRequest, TransactionResponse> getGrpcMethod(TransactionServiceGrpc.TransactionServiceBlockingStub stub) {
                return stub::doFinancialTransaction;
            }
        }.call(grpcResponse -> {
            CashoutResponse cashoutResponse = new CashoutResponse(grpcResponse.getStatus(), grpcResponse.getMessage());
            cashoutResponse.setAppliedAmount(grpcResponse.getAppliedAmount());
            cashoutResponse.setRemainedBalance(grpcResponse.getRemainedBalance());
            cashoutResponse.setTranId(grpcResponse.getTranId());
            cashoutResponse.setReferenceTranId(grpcResponse.getRefTranId());
            return cashoutResponse;
        });
    }

    @Override
    public PurchaseResponse doPurchaseTransaction(PurchaseRequest purchaseRequest) {
        LOGGER.info("Purchase transaction with gRPC");

        return new GrpcServer<com.asan.cms.grpc.TransactionRequest, TransactionResponse>(endpoint) {
            @Override
            com.asan.cms.grpc.TransactionRequest getGrpcRequest() {
                return grpcTransactionGenerator.purchaseTransaction(
                        purchaseRequest.getCardNo(),
                        purchaseRequest.getAmount(),
                        purchaseRequest.getGateway(),
                        purchaseRequest.getService(),
                        purchaseRequest.getReferenceTransactionId(),
                        purchaseRequest.getHost(),
                        purchaseRequest.getRrn()
                );
            }

            @Override
            Function<com.asan.cms.grpc.TransactionRequest, TransactionResponse> getGrpcMethod(TransactionServiceGrpc.TransactionServiceBlockingStub stub) {
                return stub::doFinancialTransaction;
            }
        }.call(grpcResponse -> {
            PurchaseResponse purchaseResponse = new PurchaseResponse(grpcResponse.getStatus(), grpcResponse.getMessage());
            purchaseResponse.setAppliedAmount(grpcResponse.getAppliedAmount());
            purchaseResponse.setRemainedBalance(grpcResponse.getRemainedBalance());
            purchaseResponse.setTranId(grpcResponse.getTranId());
            purchaseResponse.setReferenceTranId(grpcResponse.getRefTranId());
            return purchaseResponse;
        });
    }

    @Override
    public BalanceInquiryResponse doBalanceInquiryTransaction(BalanceInquiryRequest balanceInquiryRequest) {
        LOGGER.info("BalanceInquiry transaction with gRPC");

        return new GrpcServer<com.asan.cms.grpc.BalanceInquiryRequest, TransactionResponse>(endpoint) {
            @Override
            com.asan.cms.grpc.BalanceInquiryRequest getGrpcRequest() {
                return grpcTransactionGenerator.balanceInquiryTransaction(
                        balanceInquiryRequest.getCardNo(),
                        balanceInquiryRequest.getGateway(),
                        balanceInquiryRequest.getService(),
                        balanceInquiryRequest.getReferenceTransactionId(),
                        balanceInquiryRequest.getHost()
                );
            }

            @Override
            Function<com.asan.cms.grpc.BalanceInquiryRequest, TransactionResponse> getGrpcMethod(TransactionServiceGrpc.TransactionServiceBlockingStub stub) {
                return stub::doBalanceInquiry;
            }
        }.call(grpcResponse -> {
            BalanceInquiryResponse balanceInquiryResponse = new BalanceInquiryResponse(grpcResponse.getStatus(), grpcResponse.getMessage());
            balanceInquiryResponse.setAppliedAmount(grpcResponse.getAppliedAmount());
            balanceInquiryResponse.setRemainedBalance(grpcResponse.getRemainedBalance());
            balanceInquiryResponse.setTranId(grpcResponse.getTranId());
            balanceInquiryResponse.setReferenceTranId(grpcResponse.getRefTranId());
            return balanceInquiryResponse;
        });
    }

    @Override
    public StatementResponse doStatementTransaction(StatementRequest statementRequest) {
        LOGGER.info("Statement transaction with gRPC");

        return new GrpcServer<com.asan.cms.grpc.StatementRequest, TransactionResponse>(endpoint) {
            @Override
            com.asan.cms.grpc.StatementRequest getGrpcRequest() {
                return grpcTransactionGenerator.statementTransaction(statementRequest.getCardNo());
            }

            @Override
            Function<com.asan.cms.grpc.StatementRequest, TransactionResponse> getGrpcMethod(TransactionServiceGrpc.TransactionServiceBlockingStub stub) {
                return stub::doStatement;
            }
        }.call(grpcResponse -> {
            StatementResponse statementResponse = new StatementResponse(grpcResponse.getStatus(), grpcResponse.getMessage());
            statementResponse.setAppliedAmount(grpcResponse.getAppliedAmount());
            statementResponse.setRemainedBalance(grpcResponse.getRemainedBalance());
            statementResponse.setTranId(grpcResponse.getTranId());
            statementResponse.setReferenceTranId(grpcResponse.getRefTranId());
            return statementResponse;
        });
    }

    @Override
    public FundTransferResponse doFundTransferTransaction(FundTransferRequest fundTransferRequest) {
        return new GrpcServer<com.asan.cms.grpc.FundTransferRequest, TransactionResponse>(endpoint) {
            @Override
            com.asan.cms.grpc.FundTransferRequest getGrpcRequest() {
                return grpcTransactionGenerator.fundTransferTransaction(
                        fundTransferRequest.getSourceCard(),
                        fundTransferRequest.getDestinationCard(),
                        fundTransferRequest.getAmount(),
                        fundTransferRequest.getGateway(),
                        fundTransferRequest.getService(),
                        fundTransferRequest.getReferenceTransactionId(),
                        fundTransferRequest.getHost()
                );
            }

            @Override
            Function<com.asan.cms.grpc.FundTransferRequest, TransactionResponse> getGrpcMethod(TransactionServiceGrpc.TransactionServiceBlockingStub stub) {
                return stub::doFundTransfer;
            }
        }.call(grpcResponse -> {
            FundTransferResponse fundTransferResponse = new FundTransferResponse(grpcResponse.getStatus(), grpcResponse.getMessage());
            fundTransferResponse.setAppliedAmount(grpcResponse.getAppliedAmount());
            fundTransferResponse.setRemainedBalance(grpcResponse.getRemainedBalance());
            fundTransferResponse.setTranId(grpcResponse.getTranId());
            fundTransferResponse.setReferenceTranId(grpcResponse.getRefTranId());
            return fundTransferResponse;
        });
    }

    @Override
    public TransactionInquiryResponse doInquiryTransaction(TransactionRequest transactionRequest) {
        return new GrpcServer<com.asan.cms.grpc.TransactionInquiryRequest, com.asan.cms.grpc.TransactionInquiryResponse>(endpoint) {
            @Override
            com.asan.cms.grpc.TransactionInquiryRequest getGrpcRequest() {
                return grpcTransactionGenerator.inquiryTransaction(
                        transactionRequest.getGateway(),
                        transactionRequest.getService(),
                        transactionRequest.getReferenceTransactionId(),
                        transactionRequest.getHost()
                );
            }

            @Override
            Function<com.asan.cms.grpc.TransactionInquiryRequest, com.asan.cms.grpc.TransactionInquiryResponse> getGrpcMethod(TransactionServiceGrpc.TransactionServiceBlockingStub stub) {
                return stub::inquiryTransaction;
            }
        }.call(grpcTransactionInquiryResponse -> {
            TransactionInquiryResponse transactionInquiryResponse = new TransactionInquiryResponse();
            transactionInquiryResponse.setStatus(grpcTransactionInquiryResponse.getStatus());
            transactionInquiryResponse.setTransactionStatus(grpcTransactionInquiryResponse.getTranStatus());
            transactionInquiryResponse.setRrn(grpcTransactionInquiryResponse.getRrn());
            transactionInquiryResponse.setAmount(grpcTransactionInquiryResponse.getAmount());
            transactionInquiryResponse.setBalance(grpcTransactionInquiryResponse.getBalance());
            return transactionInquiryResponse;
        });
    }

    @Override
    public ReversalResponse doReverseTransaction(FinancialRequest financialRequest) {
        return new GrpcServer<com.asan.cms.grpc.TransactionRequest, TransactionResponse>(endpoint) {
            @Override
            com.asan.cms.grpc.TransactionRequest getGrpcRequest() {
                return grpcTransactionGenerator.reversalTransaction(
                        grpcTransactionGenerator.financialTransaction(
                                TransactionProcessTypeEnum.valueOf(financialRequest.getProcessingCode()),
                                financialRequest.getCardNo(),
                                financialRequest.getAmount(),
                                financialRequest.getGateway(),
                                financialRequest.getService(),
                                financialRequest.getReferenceTransactionId(),
                                financialRequest.getHost(),
                                financialRequest.getRrn()
                        )
                );
            }

            @Override
            Function<com.asan.cms.grpc.TransactionRequest, TransactionResponse> getGrpcMethod(TransactionServiceGrpc.TransactionServiceBlockingStub stub) {
                return stub::doTransactionReverse;
            }
        }.call(grpcReversalResponse -> {
            com.asan.cms.dto.ReversalResponse reversalResponse = new com.asan.cms.dto.ReversalResponse(grpcReversalResponse.getStatus(), grpcReversalResponse.getMessage());
            reversalResponse.setAppliedAmount(grpcReversalResponse.getAppliedAmount());
            reversalResponse.setRemainedBalance(grpcReversalResponse.getRemainedBalance());
            reversalResponse.setTranId(grpcReversalResponse.getTranId());
            reversalResponse.setReferenceTranId(grpcReversalResponse.getRefTranId());
            return reversalResponse;
        });
    }
}
