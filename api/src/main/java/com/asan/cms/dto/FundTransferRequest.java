package com.asan.cms.dto;

public class FundTransferRequest extends FinancialRequest {
    private String sourceCard;
    private String destinationCard;

    public FundTransferRequest() {
    }

    public FundTransferRequest(Short gateway, Integer service, Long referenceTransactionId, Integer host, String rrn) {
        super(gateway, service, referenceTransactionId, host, rrn);
    }

    public String getSourceCard() {
        return sourceCard;
    }

    public void setSourceCard(String sourceCard) {
        this.sourceCard = sourceCard;
    }

    public String getDestinationCard() {
        return destinationCard;
    }

    public void setDestinationCard(String destinationCard) {
        this.destinationCard = destinationCard;
    }
}
