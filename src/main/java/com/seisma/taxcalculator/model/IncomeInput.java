package com.seisma.taxcalculator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class IncomeInput {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Min(1)
    private int annualSalary;

    @Min(1)
    @Max(99)
    @JsonProperty("super")
    private int superRate;

    @NotBlank
    private String paymentPeriod;
}
