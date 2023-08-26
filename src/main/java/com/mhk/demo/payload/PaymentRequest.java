package com.mhk.demo.payload;

import com.mhk.demo.utils.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    private long orderId;
    private double amount;
    private String referenceNumber;
    private PaymentMode paymentMode;

}
