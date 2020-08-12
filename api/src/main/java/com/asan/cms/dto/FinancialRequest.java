package com.asan.cms.dto;

public abstract class FinancialRequest extends TransactionRequest {
    private long amount;
    private String rrn;

    public FinancialRequest() {
    }

    public FinancialRequest(Short gateway, Integer service, Long referenceTransactionId, Integer host, String rrn) {
        super(gateway, service, referenceTransactionId, host);
        this.rrn = rrn;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }
}
