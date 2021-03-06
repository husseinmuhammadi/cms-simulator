package com.asan.cms.web.controller;

import com.asan.cms.dto.*;
import com.asan.cms.rpc.TransactionGrpcService;
import com.asan.cms.rpc.TransactionReferenceGenerator;
import com.asan.cms.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    TransactionGrpcService grpcService;

    @Autowired
    TransactionReferenceGenerator referenceGenerator;

    @GetMapping("/{processingCode}")
    public String entry(Model model, @PathVariable("processingCode") String processingCode) {
        LOGGER.info("Processing Code: {}", processingCode);
        long referenceTransactionId = Long.parseLong(referenceGenerator.getRandomRefTranId());
        String rrn = referenceGenerator.getRandomRRN();

        if (processingCode.equalsIgnoreCase("payment")) {
            model.addAttribute("payment", prepare(new PaymentRequest((short) 40, 16, referenceTransactionId, 99, rrn)));
            return "transaction/payment";
        } else if (processingCode.equalsIgnoreCase("deposit")) {
            model.addAttribute("deposit", prepare(new DepositRequest((short) 40, 16, referenceTransactionId, 99, rrn)));
            return "transaction/deposit";
        } else if (processingCode.equalsIgnoreCase("cashout")) {
            model.addAttribute("cashout", prepare(new CashoutRequest((short) 40, 16, referenceTransactionId, 99, rrn)));
            return "transaction/cashout";
        } else if (processingCode.equalsIgnoreCase("purchase")) {
            model.addAttribute("purchase", prepare(new PurchaseRequest((short) 40, 16, referenceTransactionId, 99, rrn)));
            return "transaction/purchase";
        } else if (processingCode.equalsIgnoreCase("balanceInquiry")) {
            model.addAttribute("balanceInquiry", prepare(new BalanceInquiryRequest((short) 40, 16, referenceTransactionId, 99)));
            return "transaction/balance-inquiry";
        } else if (processingCode.equalsIgnoreCase("statement")) {
            model.addAttribute("statement", prepare(new StatementRequest()));
            return "transaction/statement";
        } else if (processingCode.equalsIgnoreCase("fundTransfer")) {
            model.addAttribute("fundTransfer", prepare(new FundTransferRequest((short) 40, 16, referenceTransactionId, 99, rrn)));
            return "transaction/fund-transfer";
        }
        return null;
    }

    private TransactionRequest prepare(FinancialRequest financialRequest) {
        financialRequest.setCardNo("9832551217745378");
        financialRequest.setAmount(new Random().nextInt(100) + 1);
        return financialRequest;
    }

    private TransactionRequest prepare(FundTransferRequest fundTransferRequest) {
        fundTransferRequest.setSourceCard("9832551217745378");
        fundTransferRequest.setDestinationCard("9832553325401928");
        fundTransferRequest.setAmount(new Random().nextInt(100) + 1);
        return fundTransferRequest;
    }

    private TransactionRequest prepare(AuthorizationRequest authorizationRequest) {
        authorizationRequest.setCardNo("9832551217745378");
        return authorizationRequest;
    }

    @PostMapping("/payment")
    public String payment(Model model, PaymentRequest request) {
        PaymentResponse response = grpcService.doPaymentTransaction(request);
        model.addAttribute("response", response);
        return "fragments/transaction/transaction-response :: transaction-response";
    }

    @PostMapping("/deposit")
    public String deposit(Model model, DepositRequest request) {
        DepositResponse response = grpcService.doDepositTransaction(request);
        model.addAttribute("response", response);
        return "fragments/transaction/transaction-response :: transaction-response";
    }

    @PostMapping("/cashout")
    public String deposit(Model model, CashoutRequest request) {
        CashoutResponse response = grpcService.doCashoutTransaction(request);
        model.addAttribute("response", response);
        return "fragments/transaction/transaction-response :: transaction-response";
    }

    @PostMapping("/purchase")
    public String purchase(Model model, PurchaseRequest request) {
        PurchaseResponse response = grpcService.doPurchaseTransaction(request);
        model.addAttribute("response", response);
        return "fragments/transaction/transaction-response :: transaction-response";
    }

    @PostMapping("/balanceInquiry")
    public String balanceInquiry(Model model, BalanceInquiryRequest request) {
        BalanceInquiryResponse response = grpcService.doBalanceInquiryTransaction(request);
        model.addAttribute("response", response);
        return "fragments/transaction/transaction-response :: transaction-response";
    }

    @PostMapping("/statement")
    public String statement(Model model, StatementRequest request) {
        StatementResponse response = grpcService.doStatementTransaction(request);
        model.addAttribute("response", response);
        return "fragments/transaction/transaction-response :: transaction-response";
    }

    @PostMapping("/fundTransfer")
    public String fundTransfer(Model model, FundTransferRequest request) {
        FundTransferResponse response = grpcService.doFundTransferTransaction(request);
        model.addAttribute("response", response);
        return "fragments/transaction/transaction-response :: transaction-response";
    }

    @PostMapping("/{processing-code}/inquiry")
    public String inquiry(Model model, TransactionInquiryRequest request, @PathVariable("processing-code") String processingCode) {
        LOGGER.info("Inquiring transaction: {}", processingCode);
        TransactionInquiryResponse response = grpcService.doInquiryTransaction(request);
        model.addAttribute("response", response);
        return "fragments/transaction/transaction-inquiry-response :: transaction-inquiry-response";
    }

    @PostMapping("/{processing-code}/reverse")
    public String reverse(Model model, ReversalRequest reversalRequest, @PathVariable("processing-code") String processingCode) {
        LOGGER.info("Reversing transaction: {}", processingCode);
        reversalRequest.setProcessingCode(ProcessingCode.valueOf(StringUtil.camelToSnake(processingCode).toUpperCase()).getValue());
        reversalRequest.setCardNo(Optional.ofNullable(reversalRequest.getCardNo()).orElse(""));
        ReversalResponse reversalResponse = grpcService.doReverseTransaction(reversalRequest);
        model.addAttribute("response", reversalResponse);
        return "fragments/transaction/transaction-response :: transaction-response";
    }
}

enum ProcessingCode {
    PAYMENT(1),
    PURCHASE(2),
    DEPOSIT(3),
    CASHOUT(8),
    FUND_TRANSFER(5),
    ;

    private final int value;

    ProcessingCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}