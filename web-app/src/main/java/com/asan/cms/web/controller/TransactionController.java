package com.asan.cms.web.controller;

import com.asan.cms.dto.PaymentRequest;
import com.asan.cms.dto.PaymentResponse;
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
        }
        return null;
    }

    @PostMapping("/payment")
    public String transaction(Model model, PaymentRequest paymentRequest) {
        PaymentResponse response = grpcService.doPaymentTransaction(paymentRequest);
        model.addAttribute("response", response);
        return "fragments/transaction/transaction-response :: transaction-response";
    }
}
