package com.seisma.taxcalculator.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IncomeResponse {

    private Employee employee;

    private String fromDate;

    private String toDate;

    private int grossIncome;

    private int incomeTax;

    private int superAnnuation;

    private int netIncome;
}
