package com.seisma.taxcalculator.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaxInfo {
    private int start;
    private double rate;
}
