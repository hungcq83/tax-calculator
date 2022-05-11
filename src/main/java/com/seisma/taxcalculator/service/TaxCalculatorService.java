package com.seisma.taxcalculator.service;

import org.springframework.stereotype.Service;

@Service
public class TaxCalculatorService {

    private static final double SUPER_RATE = 0.9;

    public double calculateTax(double salary) {
        return SUPER_RATE * salary;
    }
}
