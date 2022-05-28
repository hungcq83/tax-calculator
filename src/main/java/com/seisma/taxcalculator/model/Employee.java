package com.seisma.taxcalculator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Min(1)
    private int annualSalary;

    @Min(1)
    @Max(12)
    private int paymentMonth;

    @DecimalMin(value = "0.00")
    @DecimalMax(value = "0.50")
    private float superRate;
}
