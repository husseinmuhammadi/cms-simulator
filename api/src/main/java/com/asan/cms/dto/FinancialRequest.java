package com.asan.cms.dto;

public abstract class FinancialRequest extends TransactionRequest {
    private long amount;

    public FinancialRequest() {
        super();
    }

    public FinancialRequest(Short gateway, Integer service, Long referenceTransactionId, Integer host) {
        super(gateway, service, referenceTransactionId, host);
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
