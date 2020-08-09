package com.asan.cms.dto;

public class TransactionResponse {
    final int status;
    final String message;

    private long appliedAmount;
    private long remainedBalance;
    private long tranId;
    private String referenceTranId;

    public TransactionResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setAppliedAmount(long appliedAmount) {
        this.appliedAmount = appliedAmount;
    }

    public long getAppliedAmount() {
        return appliedAmount;
    }

    public void setRemainedBalance(long remainedBalance) {
        this.remainedBalance = remainedBalance;
    }

    public long getRemainedBalance() {
        return remainedBalance;
    }

    public void setTranId(long tranId) {
        this.tranId = tranId;
    }

    public long getTranId() {
        return tranId;
    }

    public void setReferenceTranId(String referenceTranId) {
        this.referenceTranId = referenceTranId;
    }

    public String getReferenceTranId() {
        return referenceTranId;
    }
}
