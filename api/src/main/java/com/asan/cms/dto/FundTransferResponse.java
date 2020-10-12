package com.asan.cms.dto;

public class FundTransferResponse extends TransactionResponse {
    public FundTransferResponse(int status, String message) {
        super(status, message);
    }

    static class FundTransferResponseBuilder {
        public FundTransferResponseBuilder(int status, String message) {
        }
    }
}
