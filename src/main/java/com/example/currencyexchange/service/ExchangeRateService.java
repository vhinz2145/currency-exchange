package com.example.currencyexchange.service;

import com.example.currencyexchange.model.ExchangeRate;
import com.example.currencyexchange.repository.ExchangeRateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public List<ExchangeRate> getExchangeRatesBySource(String sourceCurrency) {
        return exchangeRateRepository.findBySourceCurrency(sourceCurrency);
    }

    public List<ExchangeRate> getExchangeRatesbySourceAndDate(String sourceCurrency, LocalDate cutOffDate) {
        return exchangeRateRepository.findBySourceCurrencyAndCutOffDate(sourceCurrency, cutOffDate);
    }

    public List<ExchangeRate> getExchangeRatesBySourceAndTarget(String sourceCurrency, String targetCurrency) {
        return exchangeRateRepository.findBySourceCurrencyAndTargetCurrency(sourceCurrency, targetCurrency);
    }

    @Transactional
    public List<ExchangeRate> getFilteredExchangeRates(String sourceCurrency, String targetCurrency, LocalDate date) {
        if (targetCurrency == null && date == null) {
            return exchangeRateRepository.findBySourceCurrency(sourceCurrency);
        } else if (targetCurrency == null) {
            return exchangeRateRepository.findBySourceCurrencyAndCutOffDate(sourceCurrency, date);
        } else if (date == null) {
            return exchangeRateRepository.findBySourceCurrencyAndTargetCurrency(sourceCurrency, targetCurrency);
        } else {
            return exchangeRateRepository.findBySourceCurrencyAndTargetCurrencyAndCutOffDate(sourceCurrency, targetCurrency, date);
        }
    }
}