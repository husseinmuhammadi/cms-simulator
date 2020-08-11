package com.asan.cms.dto;

public class CashoutRequest extends FinancialRequest {
    public CashoutRequest() {
    }

    public CashoutRequest(Short gateway, Integer service, Long referenceTransactionId, Integer host) {
        super(gateway, service, referenceTransactionId, host);
    }
}
