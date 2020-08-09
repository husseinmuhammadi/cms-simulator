package com.asan.cms.rpc;

import com.asan.cms.dto.PaymentRequest;
import com.asan.cms.dto.PaymentResponse;

public interface TransactionGrpcService {

    PaymentResponse doPaymentTransaction(PaymentRequest paymentRequest);

}
