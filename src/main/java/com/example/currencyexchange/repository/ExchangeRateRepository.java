package com.example.currencyexchange.repository;

import com.example.currencyexchange.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
        List<ExchangeRate> findBySourceCurrency(String sourceCurrency);
        List<ExchangeRate> findBySourceCurrencyAndCutOffDate(String sourceCurrency, LocalDate cutOffDate);
        List<ExchangeRate> findBySourceCurrencyAndTargetCurrency(String sourceCurrency, String targetCurrency);
        List<ExchangeRate> findBySourceCurrencyAndTargetCurrencyAndCutOffDate(String sourceCurrency, String targetCurrency, LocalDate cutOffDate);

        @Query("SELECT e FROM ExchangeRate e WHERE e.sourceCurrency = :sourceCurrency " +
                "AND e.targetCurrency = :targetCurrency " +
                "AND FUNCTION('YEAR', e.cutOffDate) = :year " +
                "AND FUNCTION('MONTH', e.cutOffDate) = :month " +
                "AND FUNCTION('DAY', e.cutOffDate) = :day")
       
        List<ExchangeRate> findBySourceCurrencyAndTargetCurrencyAndYearAndMonthAndDay(
        @Param("sourceCurrency") String sourceCurrency, 
        @Param("targetCurrency") String targetCurrency, 
        @Param("year") int year, 
        @Param("month") int month,
        @Param("day") int day 
        );

        @Query("SELECT e FROM ExchangeRate e WHERE e.sourceCurrency = :sourceCurrency " +
                "AND e.targetCurrency = :targetCurrency " +
                "AND FUNCTION('YEAR', e.cutOffDate) = :year")
        List<ExchangeRate> findBySourceCurrencyAndTargetCurrencyAndYear(
        @Param("sourceCurrency") String sourceCurrency, 
        @Param("targetCurrency") String targetCurrency, 
        @Param("year") int year 
        );

        @Query("SELECT e FROM ExchangeRate e WHERE e.sourceCurrency = :sourceCurrency " +
                "AND e.targetCurrency = :targetCurrency " +
                "AND FUNCTION('YEAR', e.cutOffDate) = :year " +
                "AND FUNCTION('MONTH', e.cutOffDate) = :month")
        List<ExchangeRate> findBySourceCurrencyAndTargetCurrencyAndYearAndMonth(
        @Param("sourceCurrency") String sourceCurrency, 
        @Param("targetCurrency") String targetCurrency, 
        @Param("year") int year, 
        @Param("month") int month 
        );
        }
