package com.vb;

import com.vb.service.ExchangeRateService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Runner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        ExchangeRateService exchangeRateService = new ExchangeRateService(restTemplate);

        // Polymorphism: reference as Transaction
        Transaction payment = new PaymentTransaction(100.0,"USD", "THB", exchangeRateService);
        Transaction loan = new LoanTransaction(5000.0);

        payment.process();
        loan.process();

        System.out.println("Payment Status: " + payment.getStatus());
        System.out.println("Loan Status: " + loan.getStatus());
    }
}
