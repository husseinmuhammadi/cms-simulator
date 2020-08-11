package com.asan.cms.web.controller;

import com.asan.cms.dto.*;
import com.asan.cms.rpc.TransactionGrpcService;
import com.asan.cms.rpc.TransactionReferenceGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        if (processingCode.equalsIgnoreCase("payment")) {
            model.addAttribute("payment", new PaymentRequest((short) 40, 16, referenceTransactionId, 99));
            return "transaction/payment";
        } else if (processingCode.equalsIgnoreCase("deposit")) {
            model.addAttribute("deposit", new DepositRequest((short) 40, 16, referenceTransactionId, 99));
            return "transaction/deposit";
        } else if (processingCode.equalsIgnoreCase("cashout")) {
            model.addAttribute("cashout", new CashoutRequest((short) 40, 16, referenceTransactionId, 99));
            return "transaction/cashout";
        } else if (processingCode.equalsIgnoreCase("purchase")) {
            model.addAttribute("purchase", new PurchaseRequest((short) 40, 16, referenceTransactionId, 99));
            return "transaction/purchase";
        } else if (processingCode.equalsIgnoreCase("balanceInquiry")) {
            model.addAttribute("balanceInquiry", new BalanceInquiryRequest((short) 40, 16, referenceTransactionId, 99));
            return "transaction/balance-inquiry";
        } else if (processingCode.equalsIgnoreCase("statement")) {
            model.addAttribute("statement", new StatementRequest());
            return "transaction/statement";
        } else if (processingCode.equalsIgnoreCase("fundTransfer")) {
            model.addAttribute("fundTransfer", new FundTransferRequest());
            return "transaction/fund-transfer";
        }
        return null;
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

    @PostMapping("/inquiry")
    public String inquiry(Model model, FundTransferRequest request) {
        // FundTransferResponse response = grpcService.doInquiryTransaction(request);
        // model.addAttribute("response", response);
        return "fragments/transaction/transaction-response :: transaction-response";
    }
}
