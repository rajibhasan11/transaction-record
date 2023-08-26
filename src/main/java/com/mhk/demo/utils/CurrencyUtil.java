package com.mhk.demo.utils;

public class CurrencyUtil {

    public static double getAmountInUSD(double amountInSAR) {
        // 1 SAR = 0.26666667 USD (fixed rate)
        return  (0.26666667 * amountInSAR);
    }
}
