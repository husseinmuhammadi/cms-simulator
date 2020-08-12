package com.asan.cms.dto;

public class ReversalRequest extends FinancialRequest {
    public ReversalRequest() {
    }

    public ReversalRequest(Short gateway, Integer service, Long referenceTransactionId, Integer host, String rrn) {
        super(gateway, service, referenceTransactionId, host, rrn);
    }
}
