package com.mhk.demo.service;

import com.mhk.demo.payload.PaymentRequest;
import com.mhk.demo.payload.PaymentResponse;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId, String currency);
}
