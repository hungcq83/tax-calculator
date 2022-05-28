package com.seisma.taxcalculator.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MonthInfo {
    private String firstDay;
    private String lastDay;
}
