package com.mhk.demo.controller;

import com.mhk.demo.payload.PaymentResponse;
import com.mhk.demo.payload.ReportResponse;
import com.mhk.demo.service.PaymentService;
import com.mhk.demo.service.ReportService;
import com.mhk.demo.utils.CurrencyUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
@Log4j2
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<ReportResponse> getTotal() {

        log.info("ReportController | getTotal");

        ReportResponse response = reportService.getTotal();

        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }
}
