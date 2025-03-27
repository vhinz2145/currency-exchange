package com.example.currencyexchange.controller;

import com.example.currencyexchange.model.ExchangeRate;
import com.example.currencyexchange.model.ExchangeRateRequest;
import com.example.currencyexchange.service.ExchangeRateService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
            }

        @GetMapping({"/exchange-rate/{source}", "/exchange-rate/{source}/{target}", "/exchange-rate/{source}/{target}/{year}",
                "/exchange-rate/{source}/{target}/{year}/{month}", "/exchange-rate/{source}/{target}/{year}/{month}/{day}"})
    public ResponseEntity<List<ExchangeRate>> getExchangeRates(
        @PathVariable String source,
        @PathVariable(required = false) String target,
        @PathVariable(required = false) Integer year,
        @PathVariable(required = false) Integer month,
        @PathVariable(required = false) Integer day,
            HttpServletRequest request,
            HttpServletResponse response) {
        List<ExchangeRate> exchangeRates = exchangeRateService.getFilteredExchangeRates(source, target, year, month, day);

        if (exchangeRates.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exchangeRates);
                }

        String eTag = "W/\"" + exchangeRates.hashCode() + "\"";
        response.setHeader(HttpHeaders.ETAG, eTag);

        String ifNoneMatch = request.getHeader(HttpHeaders.IF_NONE_MATCH);
        if (ifNoneMatch != null && ifNoneMatch.equals(eTag)) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
                }
            return ResponseEntity.ok().body(exchangeRates);
                }

        @PostMapping("/exchange-rate")
    public ResponseEntity<?> handleExchangeRate(@RequestBody ExchangeRateRequest request) {
            String[] sources = request.getSource().split("/");
            LocalDate cutOffDate = request.getCutOffDate(); 
            String target = request.getTarget();
            List<ExchangeRate> exchangeRates = new ArrayList<>();
                
        for (String source : sources) {
                source = source.trim();
        if (target == null || target.isEmpty()) {
            List<ExchangeRate> rates = (cutOffDate == null) 
            ? exchangeRateService.getExchangeRatesBySource(source) 
            : exchangeRateService.getExchangeRatesBySourceAndDate(source, cutOffDate);
                                
            exchangeRates.addAll(rates);
            } 
        else {
            ExchangeRate savedRate = exchangeRateService.saveExchangeRate(source, target, cutOffDate);
            exchangeRates.add(savedRate);
            }
            }
        return exchangeRates.isEmpty()
            ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No exchange rates found.")
            : ResponseEntity.ok(exchangeRates);
            }
        }