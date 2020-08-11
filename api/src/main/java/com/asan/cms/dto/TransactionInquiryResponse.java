package com.asan.cms.dto;

public class TransactionInquiryResponse {

    private int status;
    private int transactionStatus;
    private String rrn;
    private long amount;
    private long balance;

    public TransactionInquiryResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTransactionStatus(int transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public int getTransactionStatus() {
        return transactionStatus;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getRrn() {
        return rrn;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getBalance() {
        return balance;
    }
}
