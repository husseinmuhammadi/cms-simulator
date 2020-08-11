package com.asan.cms.dto;

public abstract class FinancialRequest extends TransactionRequest {
    private long amount;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
