package com.example.currencyexchange.service;

import java.util.Map;

public class ExchangeRateApiResponse {
    
    private Map<String, Object> rates;  // The map of currency rates
    private String base;                // The base currency (e.g., USD, EUR)
    private String date;                // The date of the rates

    // Getters and 
    public Map<String, Object> getRates() {
        return rates;
    }
    public String getBase() {
        return base;
    }
    public String getDate() { 
        return date;
    }

    // Setters
    public void setRates(Map<String, Object> rates) {
        this.rates = rates;
    }
    public void setBase(String base) {
        this.base = base;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
