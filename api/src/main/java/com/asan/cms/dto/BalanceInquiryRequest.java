package com.asan.cms.dto;

public class BalanceInquiryRequest extends AuthorizationRequest {
    public BalanceInquiryRequest() {
    }

    public BalanceInquiryRequest(Short gateway, Integer service, Long referenceTransactionId, Integer host) {
        super(gateway, service, referenceTransactionId, host);
    }
}
