package com.asan.cms.dto;

public class PaymentRequest extends FinancialRequest {
    public PaymentRequest() {
    }

    public PaymentRequest(Short gateway, Integer service, Long referenceTransactionId, Integer host, String rrn) {
        super(gateway, service, referenceTransactionId, host, rrn);
    }
}
