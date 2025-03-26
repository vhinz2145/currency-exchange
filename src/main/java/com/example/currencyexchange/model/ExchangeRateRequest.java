package com.example.currencyexchange.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ExchangeRateRequest {
    private String source;
    private String target;
    private LocalDate cutOffDate;

    // Getters
    public String getSource() { 
        return source; 
    }
    public LocalDate getCutOffDate() {
        return cutOffDate; 
    }
    public String getTarget() {
        return target; 
    }

    // Setters
    public void setTarget(String target) {
        this.target = target; 
    }
    public void setCutOffDate(LocalDate cutOffDate) {
        this.cutOffDate = cutOffDate; 
    }
    public void setSource(String source) {  
        this.source = source; 
    }
}
