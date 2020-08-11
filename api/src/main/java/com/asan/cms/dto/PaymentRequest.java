package com.asan.cms.dto;

public class PaymentRequest extends FinancialRequest {
    public PaymentRequest() {
        super();
    }

    public PaymentRequest(Short gateway, Integer service, Long referenceTransactionId, Integer host) {
        super(gateway, service, referenceTransactionId, host);
    }
}
