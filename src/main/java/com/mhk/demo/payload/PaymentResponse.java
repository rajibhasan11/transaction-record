package com.mhk.demo.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mhk.demo.utils.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {

    private long paymentId;
    private String status;
    private PaymentMode paymentMode;
    private double amount;
    private Instant paymentDate;
    private long orderId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double convertedAmount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String toCurrency;

}
