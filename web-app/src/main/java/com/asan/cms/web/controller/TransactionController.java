package com.asan.cms.web.controller;

import com.asan.cms.dto.*;
import com.asan.cms.rpc.TransactionGrpcService;
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

    @GetMapping("/{processingCode}")
    public String entry(Model model, @PathVariable("processingCode") String processingCode) {
        LOGGER.info("Processing Code: {}", processingCode);
        if (processingCode.equalsIgnoreCase("payment")) {
            model.addAttribute("payment", new PaymentRequest());
            return "transaction/payment";
        } else if (processingCode.equalsIgnoreCase("deposit")) {
            model.addAttribute("deposit", new DepositRequest());
            return "transaction/deposit";
        } else if (processingCode.equalsIgnoreCase("cashout")) {
            model.addAttribute("cashout", new CashoutRequest());
            return "transaction/cashout";
        } else if (processingCode.equalsIgnoreCase("purchase")) {
            model.addAttribute("purchase", new PurchaseRequest());
            return "transaction/purchase";
        } else if (processingCode.equalsIgnoreCase("balanceInquiry")) {
            model.addAttribute("balanceInquiry", new BalanceInquiryRequest());
            return "transaction/balance-inquiry";
        }
        return null;
    }

    @PostMapping("/payment")
    public String payment(Model model, PaymentRequest paymentRequest) {
        PaymentResponse response = grpcService.doPaymentTransaction(paymentRequest);
        model.addAttribute("response", response);
        return "fragments/transaction/transaction-response :: transaction-response";
    }

    @PostMapping("/deposit")
    public String deposit(Model model, DepositRequest depositRequest) {
        DepositResponse response = grpcService.doDepositTransaction(depositRequest);
        model.addAttribute("response", response);
        return "fragments/transaction/transaction-response :: transaction-response";
    }

    @PostMapping("/cashout")
    public String deposit(Model model, CashoutRequest cashoutRequest) {
        CashoutResponse response = grpcService.doCashoutTransaction(cashoutRequest);
        model.addAttribute("response", response);
        return "fragments/transaction/transaction-response :: transaction-response";
    }

    @PostMapping("/purchase")
    public String purchase(Model model, PurchaseRequest purchaseRequest) {
        PurchaseResponse response = grpcService.doPurchaseTransaction(purchaseRequest);
        model.addAttribute("response", response);
        return "fragments/transaction/transaction-response :: transaction-response";
    }

    @PostMapping("/balanceInquiry")
    public String balanceInquiry(Model model, BalanceInquiryRequest balanceInquiryRequest) {
        BalanceInquiryResponse response = grpcService.doBalanceInquiryTransaction(balanceInquiryRequest);
        model.addAttribute("response", response);
        return "fragments/transaction/transaction-response :: transaction-response";
    }
}
