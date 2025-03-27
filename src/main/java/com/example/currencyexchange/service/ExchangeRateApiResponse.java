package com.example.currencyexchange.service;

import java.util.Map;

public class ExchangeRateApiResponse {
    
    private Map<String, Object> rates;
    private String base;                
    private String date;                

    public Map<String, Object> getRates() {
        return rates;
    }
    public String getBase() {
        return base;
    }
    public String getDate() { 
        return date;
    }

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
