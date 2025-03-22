package com.example.currencyexchange.controller;

import com.example.currencyexchange.model.ExchangeRate;
import com.example.currencyexchange.model.ExchangeRateRequest;
import com.example.currencyexchange.service.ExchangeRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/exchange-rates")
@CrossOrigin(origins = "*")  // ✅ Fixes CORS issues
public class ExchangeRateController {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateController.class);
    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    // ✅ Handles requests to "/" (root URL)
    @GetMapping("/")
    public ResponseEntity<String> handleRootRequest(HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        logger.info("Root URL '/' was accessed from IP: {}", clientIp);
        
        return ResponseEntity.ok("Welcome to the Currency Exchange API! Use /api/exchange-rates for exchange rates.");
    }

    @GetMapping({"/{source}", "/{source}/{target}", "/{source}/{target}/{year}",
                 "/{source}/{target}/{year}/{month}", "/{source}/{target}/{year}/{month}/{day}"})
    public ResponseEntity<List<ExchangeRate>> getExchangeRatesBySource(@PathVariable String source) {
        List<ExchangeRate> rates = exchangeRateService.getExchangeRatesBySource(source);
        System.out.println("Returning exchange rates: " + rates);
        return ResponseEntity.ok(rates);
    }

    // ✅ Single GET method (Handles all cases)
    @GetMapping
    public ResponseEntity<List<ExchangeRate>> getExchangeRates(
            @RequestParam String source,
            @RequestParam(required = false) String target,
            @RequestParam(required = false) String cutOffDate,
            HttpServletRequest request,
            HttpServletResponse response) {

        // Convert cutOffDate (String) to LocalDate
        LocalDate date = null;
        if (cutOffDate != null && !cutOffDate.isEmpty()) {
            date = LocalDate.parse(cutOffDate);
        }

        List<ExchangeRate> exchangeRates = exchangeRateService.getFilteredExchangeRates(source, target, date);

        if (exchangeRates.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exchangeRates);
        }

        // Set ETag for caching
        String eTag = "W/\"" + exchangeRates.hashCode() + "\"";
        response.setHeader(HttpHeaders.ETAG, eTag);

        String ifNoneMatch = request.getHeader(HttpHeaders.IF_NONE_MATCH);
        if (ifNoneMatch != null && ifNoneMatch.equals(eTag)) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

        return ResponseEntity.ok().body(exchangeRates);
    }

    // ✅ POST method (For frontend that sends JSON)
    @PostMapping
    public ResponseEntity<List<ExchangeRate>> getExchangeRatesFromPost(@RequestBody ExchangeRateRequest request) {
        String source = request.getSource();
        String target = request.getTarget();
        LocalDate cutOffDate = request.getCutOffDate();

        List<ExchangeRate> exchangeRates = exchangeRateService.getFilteredExchangeRates(source, target, cutOffDate);

        if (exchangeRates.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exchangeRates);
        }

        return ResponseEntity.ok(exchangeRates);
    }
}
