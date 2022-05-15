package com.seisma.taxcalculator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("super")
    private int superAmount;

}
