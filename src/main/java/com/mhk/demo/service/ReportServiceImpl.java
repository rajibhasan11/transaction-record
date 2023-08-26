package com.mhk.demo.service;

import com.mhk.demo.model.TransactionDetails;
import com.mhk.demo.payload.ReportResponse;
import com.mhk.demo.repository.TransactionDetailsRepository;
import com.mhk.demo.utils.CurrencyUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final TransactionDetailsRepository transactionDetailsRepository;

    @Override
    public ReportResponse getTotal() {

        log.info("ReportServiceImpl | getTotal is called");

        List<TransactionDetails> allTransactionDetails = transactionDetailsRepository.findAll();

        long count  = allTransactionDetails.size();

        double amountInSAR = allTransactionDetails.stream()
                .mapToDouble(TransactionDetails::getAmount)
                .sum();
        double amountInUSD = CurrencyUtil.getAmountInUSD(amountInSAR);

        ReportResponse reportResponse
                = ReportResponse.builder()
                .count(count)
                .totalSAR(amountInSAR)
                .totalUSD(amountInUSD)
                .build();

        log.info("ReportServiceImpl | getTotal | reportResponse: {}", reportResponse.toString());

        return reportResponse;
    }
}
