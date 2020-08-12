package com.asan.cms.dto;

public abstract class TransactionRequest {
    private String cardNo;
    private Integer processingCode;
    private Integer service;
    private Short gateway;
    private Long referenceTransactionId;
    private Integer host;

    public TransactionRequest() {
    }

    public TransactionRequest(Short gateway, Integer service, Long referenceTransactionId, Integer host) {
        this.service = service;
        this.gateway = gateway;
        this.referenceTransactionId = referenceTransactionId;
        this.host = host;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getService() {
        return service;
    }

    public void setService(Integer service) {
        this.service = service;
    }

    public Short getGateway() {
        return gateway;
    }

    public void setGateway(Short gateway) {
        this.gateway = gateway;
    }

    public Long getReferenceTransactionId() {
        return referenceTransactionId;
    }

    public void setReferenceTransactionId(Long referenceTransactionId) {
        this.referenceTransactionId = referenceTransactionId;
    }

    public Integer getHost() {
        return host;
    }

    public void setHost(Integer host) {
        this.host = host;
    }

    public Integer getProcessingCode() {
        return processingCode;
    }

    public void setProcessingCode(Integer processingCode) {
        this.processingCode = processingCode;
    }
}
