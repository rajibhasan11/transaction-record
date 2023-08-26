package com.mhk.demo.service;

import com.mhk.demo.exception.ResourceNotFoundException;
import com.mhk.demo.model.TransactionDetails;
import com.mhk.demo.payload.PaymentRequest;
import com.mhk.demo.payload.PaymentResponse;
import com.mhk.demo.repository.TransactionDetailsRepository;
import com.mhk.demo.utils.CurrencyUtil;
import com.mhk.demo.utils.PaymentMode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final TransactionDetailsRepository transactionDetailsRepository;

    @Override
    public long doPayment(PaymentRequest paymentRequest) {

        log.info("PaymentServiceImpl | doPayment is called");

        log.info("PaymentServiceImpl | doPayment | Recording Payment Details: {}", paymentRequest);

        TransactionDetails transactionDetails
                = TransactionDetails.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(paymentRequest.getAmount())
                .build();

        transactionDetails = transactionDetailsRepository.save(transactionDetails);

        log.info("Transaction Completed with Id: {}", transactionDetails.getId());

        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(long orderId, String currency) {

        log.info("PaymentServiceImpl | getPaymentDetailsByOrderId is called");

        log.info("PaymentServiceImpl | getPaymentDetailsByOrderId | Getting payment details for the Order Id: {}", orderId);

        TransactionDetails transactionDetails
                = transactionDetailsRepository.findByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + orderId));

        double amount = transactionDetails.getAmount(); // amount in SAR
        Double convertedAmount = null;
        String toCurrency = null;
        PaymentResponse paymentResponse;

        if ("USD".equalsIgnoreCase(currency)) {
            convertedAmount = CurrencyUtil.getAmountInUSD(amount);
            toCurrency = "USD";
        }

        paymentResponse = PaymentResponse.builder()
                .paymentId(transactionDetails.getId())
                .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                .paymentDate(transactionDetails.getPaymentDate())
                .orderId(transactionDetails.getOrderId())
                .status(transactionDetails.getPaymentStatus())
                .amount(amount)
                .toCurrency(toCurrency)
                .convertedAmount(convertedAmount)
                .build();

        log.info("PaymentServiceImpl | getPaymentDetailsByOrderId | paymentResponse: {}", paymentResponse.toString());

        return paymentResponse;
    }
}
