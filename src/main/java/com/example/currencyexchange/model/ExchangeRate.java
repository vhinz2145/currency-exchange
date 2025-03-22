package com.example.currencyexchange.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "exchange_rates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ExchangeRate {

    @Id

    @Column(name = "cut_off_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate cutOffDate;

    @Column(name = "source_currency", nullable = false)
    private String sourceCurrency;

    @Column(name = "target_currency", nullable = false)
    private String targetCurrency;

    @Column(name = "exchange_rate", nullable = false)
    private BigDecimal exchangeRate;
}