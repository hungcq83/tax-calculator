package com.seisma.taxcalculator.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IncomeOutput {

    private String fullName;

    private String paymentPeriod;

    private int grossIncome;

    private int incomeTax;

    private int netIncome;

    private int superAmount;

}
