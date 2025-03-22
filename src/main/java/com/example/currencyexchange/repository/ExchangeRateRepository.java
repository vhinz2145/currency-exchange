package com.example.currencyexchange.repository;

import com.example.currencyexchange.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, LocalDate> {
    List<ExchangeRate> findBySourceCurrency(String sourceCurrency); 
    List<ExchangeRate> findBySourceCurrencyAndCutOffDate(String sourceCurrency, LocalDate cutOffDate);
    List<ExchangeRate> findBySourceCurrencyAndTargetCurrency(String sourceCurrency, String targetCurrency);
    List<ExchangeRate> findBySourceCurrencyAndTargetCurrencyAndCutOffDate(String sourceCurrency, String targetCurrency, LocalDate cutOffDate);
}
