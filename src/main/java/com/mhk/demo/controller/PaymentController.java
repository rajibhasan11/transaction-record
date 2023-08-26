package com.mhk.demo.controller;

import com.mhk.demo.payload.PaymentRequest;
import com.mhk.demo.payload.PaymentResponse;
import com.mhk.demo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@Log4j2
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest) {

        log.info("PaymentController | doPayment is called");

        log.info("PaymentController | doPayment | paymentRequest : " + paymentRequest.toString());

        return new ResponseEntity<>(
                paymentService.doPayment(paymentRequest),
                HttpStatus.OK
        );
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable long orderId
            , @RequestParam(required = false, defaultValue = "SAR") String currency) {

        log.info("PaymentController | getPayment | orderId : " + orderId + " currency: " + currency);

        PaymentResponse response = paymentService.getPaymentDetailsByOrderId(orderId, currency);

        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }
}
