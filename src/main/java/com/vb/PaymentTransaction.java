package com.vb;

import com.vb.service.ExchangeRateService;

// Inheritance + Polymorphism
// subclass
public class PaymentTransaction extends Transaction {

    private final ExchangeRateService exchangeRateService;

    private final String baseCurrency;
    private final String targetCurrency;

    public PaymentTransaction(double amount,
                              String baseCurrency, String targetCurrency, ExchangeRateService exchangeRateService) {
        super(amount);
        this.exchangeRateService = exchangeRateService;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
    }

    @Override
    public void process() {
        System.out.println("[Payment] Processing payment of " + getAmount() + " " + baseCurrency);

        double rate = 0;
        try {
            rate = exchangeRateService.getExchangeRate(baseCurrency, targetCurrency);
            System.out.println("Current rate " + baseCurrency + " -> " + targetCurrency + ": " + rate);
        } catch (Exception e) {
            System.out.println("Failed to get exchange rate: " + e.getMessage());
            setStatus("FAILED");
            return;
        }

        double amountInTarget = getAmount() * rate;
        System.out.println("Amount in " + targetCurrency + ": " + amountInTarget);

        if (amountInTarget > 100000) {
            setStatus("REJECTED");
            System.out.println("Payment rejected: amount exceeds limit.");
        } else {
            setStatus("COMPLETED");
            System.out.println("Payment approved.");
        }
    }

}
