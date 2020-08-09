package com.asan.cms.dto;

public class PaymentResponse extends TransactionResponse {
    public PaymentResponse(int status, String message) {
        super(status, message);
    }
}
