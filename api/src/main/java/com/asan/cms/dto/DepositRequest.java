package com.asan.cms.dto;

public class DepositRequest extends FinancialRequest {
    public DepositRequest() {
        super();
    }

    public DepositRequest(Short gateway, Integer service, Long referenceTransactionId, Integer host) {
        super(gateway, service, referenceTransactionId, host);
    }
}
