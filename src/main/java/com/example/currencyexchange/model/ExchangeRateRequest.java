package com.example.currencyexchange.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class ExchangeRateRequest {
    private String source;
    private String target;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate cutOffDate;

    public ExchangeRateRequest() {}

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getTarget() { return target; }
    public void setTarget(String target) { this.target = target; }

    public LocalDate getCutOffDate() { return cutOffDate; }
    public void setCutOffDate(LocalDate cutOffDate) { this.cutOffDate = cutOffDate; }
}