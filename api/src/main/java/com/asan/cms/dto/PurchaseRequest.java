package com.asan.cms.dto;

public class PurchaseRequest extends FinancialRequest {
    public PurchaseRequest() {
        super();
    }

    public PurchaseRequest(Short gateway, Integer service, Long referenceTransactionId, Integer host) {
        super(gateway, service, referenceTransactionId, host);
    }
}
