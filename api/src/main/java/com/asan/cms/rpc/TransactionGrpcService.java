package com.asan.cms.rpc;

import com.asan.cms.dto.*;

public interface TransactionGrpcService {
    PaymentResponse doPaymentTransaction(PaymentRequest paymentRequest);

    DepositResponse doDepositTransaction(DepositRequest depositRequest);

    CashoutResponse doCashoutTransaction(CashoutRequest cashoutRequest);

    PurchaseResponse doPurchaseTransaction(PurchaseRequest purchaseRequest);

    BalanceInquiryResponse doBalanceInquiryTransaction(BalanceInquiryRequest balanceInquiryRequest);

    StatementResponse doStatementTransaction(StatementRequest statementRequest);

    FundTransferResponse doFundTransferTransaction(FundTransferRequest fundTransferRequest);
}
