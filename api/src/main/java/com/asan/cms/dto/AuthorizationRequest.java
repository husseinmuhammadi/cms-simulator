package com.asan.cms.dto;

public abstract class AuthorizationRequest extends TransactionRequest {
    public AuthorizationRequest() {
    }

    public AuthorizationRequest(Short gateway, Integer service, Long referenceTransactionId, Integer host) {
        super(gateway, service, referenceTransactionId, host);
    }
}
