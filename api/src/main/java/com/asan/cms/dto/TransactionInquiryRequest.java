package com.asan.cms.dto;

public class TransactionInquiryRequest extends TransactionRequest {
    public TransactionInquiryRequest() {
    }

    public TransactionInquiryRequest(Short gateway, Integer service, Long referenceTransactionId, Integer host) {
        super(gateway, service, referenceTransactionId, host);
    }
}
