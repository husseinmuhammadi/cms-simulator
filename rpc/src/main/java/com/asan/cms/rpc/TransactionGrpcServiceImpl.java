package com.asan.cms.rpc;

import com.asan.cms.grpc.*;
import com.asan.cms.rpc.configuration.GrpcEndpointConfiguration;
import com.asan.cms.type.TransactionProcessTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Function;

@Component
public class TransactionGrpcServiceImpl implements TransactionGrpcService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionGrpcServiceImpl.class);

    @Autowired
    GrpcEndpointConfiguration endpoint;

    @Autowired
    GrpcTransactionGenerator grpcTransactionGenerator;

    @Override
    public com.asan.cms.dto.PaymentResponse doPaymentTransaction(com.asan.cms.dto.PaymentRequest paymentRequest) {
        LOGGER.info("Payment transaction with gRPC");

        return new GrpcServer<TransactionRequest, TransactionResponse>(endpoint) {
            @Override
            TransactionRequest getGrpcRequest() {
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
            Function<TransactionRequest, TransactionResponse> getGrpcMethod(TransactionServiceGrpc.TransactionServiceBlockingStub stub) {
                return stub::doFinancialTransaction;
            }
        }.call(grpcResponse -> {
            com.asan.cms.dto.PaymentResponse paymentResponse = new com.asan.cms.dto.PaymentResponse(grpcResponse.getStatus(), grpcResponse.getMessage());
            paymentResponse.setAppliedAmount(grpcResponse.getAppliedAmount());
            paymentResponse.setRemainedBalance(grpcResponse.getRemainedBalance());
            paymentResponse.setTranId(grpcResponse.getTranId());
            paymentResponse.setReferenceTranId(grpcResponse.getRefTranId());
            return paymentResponse;
        });
    }

    @Override
    public com.asan.cms.dto.DepositResponse doDepositTransaction(com.asan.cms.dto.DepositRequest depositRequest) {
        LOGGER.info("Deposit transaction with gRPC");

        return new GrpcServer<TransactionRequest, TransactionResponse>(endpoint) {
            @Override
            TransactionRequest getGrpcRequest() {
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
            Function<TransactionRequest, TransactionResponse> getGrpcMethod(TransactionServiceGrpc.TransactionServiceBlockingStub stub) {
                return stub::doFinancialTransaction;
            }
        }.call(grpcResponse -> {
            com.asan.cms.dto.DepositResponse depositResponse = new com.asan.cms.dto.DepositResponse(grpcResponse.getStatus(), grpcResponse.getMessage());
            depositResponse.setAppliedAmount(grpcResponse.getAppliedAmount());
            depositResponse.setRemainedBalance(grpcResponse.getRemainedBalance());
            depositResponse.setTranId(grpcResponse.getTranId());
            depositResponse.setReferenceTranId(grpcResponse.getRefTranId());
            return depositResponse;
        });
    }

    @Override
    public com.asan.cms.dto.CashoutResponse doCashoutTransaction(com.asan.cms.dto.CashoutRequest cashoutRequest) {
        LOGGER.info("Cashout transaction with gRPC");

        return new GrpcServer<TransactionRequest, TransactionResponse>(endpoint) {
            @Override
            TransactionRequest getGrpcRequest() {
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
            Function<TransactionRequest, TransactionResponse> getGrpcMethod(TransactionServiceGrpc.TransactionServiceBlockingStub stub) {
                return stub::doFinancialTransaction;
            }
        }.call(grpcResponse -> {
            com.asan.cms.dto.CashoutResponse cashoutResponse = new com.asan.cms.dto.CashoutResponse(grpcResponse.getStatus(), grpcResponse.getMessage());
            cashoutResponse.setAppliedAmount(grpcResponse.getAppliedAmount());
            cashoutResponse.setRemainedBalance(grpcResponse.getRemainedBalance());
            cashoutResponse.setTranId(grpcResponse.getTranId());
            cashoutResponse.setReferenceTranId(grpcResponse.getRefTranId());
            return cashoutResponse;
        });
    }

    @Override
    public com.asan.cms.dto.PurchaseResponse doPurchaseTransaction(com.asan.cms.dto.PurchaseRequest purchaseRequest) {
        LOGGER.info("Purchase transaction with gRPC");

        return new GrpcServer<TransactionRequest, TransactionResponse>(endpoint) {
            @Override
            TransactionRequest getGrpcRequest() {
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
            Function<TransactionRequest, TransactionResponse> getGrpcMethod(TransactionServiceGrpc.TransactionServiceBlockingStub stub) {
                return stub::doFinancialTransaction;
            }
        }.call(grpcResponse -> {
            com.asan.cms.dto.PurchaseResponse purchaseResponse = new com.asan.cms.dto.PurchaseResponse(grpcResponse.getStatus(), grpcResponse.getMessage());
            purchaseResponse.setAppliedAmount(grpcResponse.getAppliedAmount());
            purchaseResponse.setRemainedBalance(grpcResponse.getRemainedBalance());
            purchaseResponse.setTranId(grpcResponse.getTranId());
            purchaseResponse.setReferenceTranId(grpcResponse.getRefTranId());
            return purchaseResponse;
        });
    }

    @Override
    public com.asan.cms.dto.BalanceInquiryResponse doBalanceInquiryTransaction(com.asan.cms.dto.BalanceInquiryRequest balanceInquiryRequest) {
        LOGGER.info("BalanceInquiry transaction with gRPC");

        return new GrpcServer<BalanceInquiryRequest, TransactionResponse>(endpoint) {
            @Override
            BalanceInquiryRequest getGrpcRequest() {
                return grpcTransactionGenerator.balanceInquiryTransaction(
                        balanceInquiryRequest.getCardNo(),
                        balanceInquiryRequest.getGateway(),
                        balanceInquiryRequest.getService(),
                        balanceInquiryRequest.getReferenceTransactionId(),
                        balanceInquiryRequest.getHost()
                );
            }

            @Override
            Function<BalanceInquiryRequest, TransactionResponse> getGrpcMethod(TransactionServiceGrpc.TransactionServiceBlockingStub stub) {
                return stub::doBalanceInquiry;
            }
        }.call(grpcResponse -> {
            com.asan.cms.dto.BalanceInquiryResponse balanceInquiryResponse = new com.asan.cms.dto.BalanceInquiryResponse(grpcResponse.getStatus(), grpcResponse.getMessage());
            balanceInquiryResponse.setAppliedAmount(grpcResponse.getAppliedAmount());
            balanceInquiryResponse.setRemainedBalance(grpcResponse.getRemainedBalance());
            balanceInquiryResponse.setTranId(grpcResponse.getTranId());
            balanceInquiryResponse.setReferenceTranId(grpcResponse.getRefTranId());
            return balanceInquiryResponse;
        });
    }

    @Override
    public com.asan.cms.dto.StatementResponse doStatementTransaction(com.asan.cms.dto.StatementRequest statementRequest) {
        LOGGER.info("Statement transaction with gRPC");

        return new GrpcServer<StatementRequest, TransactionResponse>(endpoint) {
            @Override
            StatementRequest getGrpcRequest() {
                return grpcTransactionGenerator.statementTransaction(statementRequest.getCardNo());
            }

            @Override
            Function<StatementRequest, TransactionResponse> getGrpcMethod(TransactionServiceGrpc.TransactionServiceBlockingStub stub) {
                return stub::doStatement;
            }
        }.call(grpcResponse -> {
            com.asan.cms.dto.StatementResponse statementResponse = new com.asan.cms.dto.StatementResponse(grpcResponse.getStatus(), grpcResponse.getMessage());
            statementResponse.setAppliedAmount(grpcResponse.getAppliedAmount());
            statementResponse.setRemainedBalance(grpcResponse.getRemainedBalance());
            statementResponse.setTranId(grpcResponse.getTranId());
            statementResponse.setReferenceTranId(grpcResponse.getRefTranId());
            return statementResponse;
        });
    }

    @Override
    public com.asan.cms.dto.FundTransferResponse doFundTransferTransaction(com.asan.cms.dto.FundTransferRequest fundTransferRequest) {
        return new GrpcServer<FundTransferRequest, TransactionResponse>(endpoint) {
            @Override
            FundTransferRequest getGrpcRequest() {
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
            Function<FundTransferRequest, TransactionResponse> getGrpcMethod(TransactionServiceGrpc.TransactionServiceBlockingStub stub) {
                return stub::doFundTransfer;
            }
        }.call(grpcResponse -> {
            com.asan.cms.dto.FundTransferResponse fundTransferResponse = new com.asan.cms.dto.FundTransferResponse(grpcResponse.getStatus(), grpcResponse.getMessage());
            fundTransferResponse.setAppliedAmount(grpcResponse.getAppliedAmount());
            fundTransferResponse.setRemainedBalance(grpcResponse.getRemainedBalance());
            fundTransferResponse.setTranId(grpcResponse.getTranId());
            fundTransferResponse.setReferenceTranId(grpcResponse.getRefTranId());
            return fundTransferResponse;
        });
    }

    @Override
    public com.asan.cms.dto.TransactionInquiryResponse doInquiryTransaction(com.asan.cms.dto.TransactionRequest transactionRequest) {
        return new GrpcServer<TransactionInquiryRequest, TransactionInquiryResponse>(endpoint) {
            @Override
            TransactionInquiryRequest getGrpcRequest() {
                return grpcTransactionGenerator.inquiryTransaction(
                        transactionRequest.getGateway(),
                        transactionRequest.getService(),
                        transactionRequest.getReferenceTransactionId(),
                        transactionRequest.getHost()
                );
            }

            @Override
            Function<TransactionInquiryRequest, TransactionInquiryResponse> getGrpcMethod(TransactionServiceGrpc.TransactionServiceBlockingStub stub) {
                return stub::inquiryTransaction;
            }
        }.call(grpcTransactionInquiryResponse -> {
            com.asan.cms.dto.TransactionInquiryResponse transactionInquiryResponse = new com.asan.cms.dto.TransactionInquiryResponse();
            transactionInquiryResponse.setStatus(grpcTransactionInquiryResponse.getStatus());
            transactionInquiryResponse.setTransactionStatus(grpcTransactionInquiryResponse.getTranStatus());
            transactionInquiryResponse.setRrn(grpcTransactionInquiryResponse.getRrn());
            transactionInquiryResponse.setAmount(grpcTransactionInquiryResponse.getAmount());
            transactionInquiryResponse.setBalance(grpcTransactionInquiryResponse.getBalance());
            return transactionInquiryResponse;
        });
    }

    @Override
    public com.asan.cms.dto.ReversalResponse doReverseTransaction(com.asan.cms.dto.FinancialRequest financialRequest) {
        return new GrpcServer<TransactionRequest, TransactionResponse>(endpoint) {
            @Override
            TransactionRequest getGrpcRequest() {
                return grpcTransactionGenerator.reversalTransaction(
                        grpcTransactionGenerator.financialTransaction(
                                Objects.requireNonNull(TransactionProcessTypeEnum.valueOf(financialRequest.getProcessingCode())),
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
            Function<TransactionRequest, TransactionResponse> getGrpcMethod(TransactionServiceGrpc.TransactionServiceBlockingStub stub) {
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
