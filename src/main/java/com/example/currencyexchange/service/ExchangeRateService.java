package com.example.currencyexchange.service;

import com.example.currencyexchange.model.ExchangeRate;
import com.example.currencyexchange.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

        private final RestTemplate restTemplate;
    
    @Value("${exchangerateapi.url}")
    private String exchangeRateApiUrl;
    
    @Value("${exchangerateapi.apiKey}")
    private String apiKey;

    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository, RestTemplate restTemplate) {
            this.exchangeRateRepository = exchangeRateRepository;
            this.restTemplate = restTemplate; }
    public List<ExchangeRate> getExchangeRatesBySource(String sourceCurrency) 
            { return exchangeRateRepository.findBySourceCurrency(sourceCurrency); }
    public List<ExchangeRate> getExchangeRatesBySourceAndDate(String sourceCurrency, LocalDate cutOffDate) 
            { return exchangeRateRepository.findBySourceCurrencyAndCutOffDate(sourceCurrency, cutOffDate); }
    public List<ExchangeRate> getExchangeRatesBySourceAndTarget(String sourceCurrency, String targetCurrency) 
            { return exchangeRateRepository.findBySourceCurrencyAndTargetCurrency(sourceCurrency, targetCurrency); }

    @Transactional
    public List<ExchangeRate> getFilteredExchangeRates(String sourceCurrency, String targetCurrency, LocalDate date) {
        if (targetCurrency == null && date == null) 
            { return exchangeRateRepository.findBySourceCurrency(sourceCurrency); } 
        else if (targetCurrency == null) 
            { return exchangeRateRepository.findBySourceCurrencyAndCutOffDate(sourceCurrency, date); } 
        else if (date == null) 
            { return exchangeRateRepository.findBySourceCurrencyAndTargetCurrency(sourceCurrency, targetCurrency); } 
        else { return exchangeRateRepository.findBySourceCurrencyAndTargetCurrencyAndCutOffDate(sourceCurrency, targetCurrency, date); }
            }

    @Transactional
    public List<ExchangeRate> getFilteredExchangeRates(String sourceCurrency, String targetCurrency, Integer year, Integer month, Integer day) {
        if (year != null && month != null && day != null) 
            { return exchangeRateRepository.findBySourceCurrencyAndTargetCurrencyAndYearAndMonthAndDay(sourceCurrency, targetCurrency, year, month, day); } 
        else if (year != null && month != null) 
            { return exchangeRateRepository.findBySourceCurrencyAndTargetCurrencyAndYearAndMonth(sourceCurrency, targetCurrency, year, month); } 
        else if (year != null) 
            { return exchangeRateRepository.findBySourceCurrencyAndTargetCurrencyAndYear(sourceCurrency, targetCurrency, year); } 
        else if (targetCurrency != null) 
            { return exchangeRateRepository.findBySourceCurrencyAndTargetCurrency(sourceCurrency, targetCurrency); } 
        else 
            { return exchangeRateRepository.findBySourceCurrency(sourceCurrency); }
            }

    public BigDecimal fetchExchangeRateFromApi(String sourceCurrency, String targetCurrency, LocalDate cutOffDate) {
        String url = String.format("%s/latest?base=%s&symbols=%s&apikey=%s", 
                        exchangeRateApiUrl, sourceCurrency, targetCurrency, apiKey);
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        Map<String, Object> rates = (Map<String, Object>) response.getBody().get("rates");
        return new BigDecimal(rates.get(targetCurrency).toString());
    }

    @Transactional
    public ExchangeRate saveExchangeRate(String sourceCurrency, String targetCurrency, LocalDate cutOffDate) {
        BigDecimal exchangeRate = fetchExchangeRateFromApi(sourceCurrency, targetCurrency, cutOffDate);
        ExchangeRate exchangeRateEntity = new ExchangeRate();
        exchangeRateEntity.setSourceCurrency(sourceCurrency);
        exchangeRateEntity.setTargetCurrency(targetCurrency);
        exchangeRateEntity.setCutOffDate(cutOffDate);
        exchangeRateEntity.setExchangeRate(exchangeRate);
        return exchangeRateRepository.save(exchangeRateEntity);
    }
}
