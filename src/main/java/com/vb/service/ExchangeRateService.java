package com.vb.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.Map;

@Service
public class ExchangeRateService {

    private RestTemplate restTemplate;

    public ExchangeRateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "exchangeRate", fallbackMethod = "fallbackGetRate")
    public double getExchangeRate(String baseCurrency, String targetCurrency) {
        String url = "https://api.exchangerate-api.com/v4/latest/" + baseCurrency;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        Map<String, Double> rates = (Map<String, Double>) response.get("rates");
        return rates.getOrDefault(targetCurrency, 0.0);
    }

}
