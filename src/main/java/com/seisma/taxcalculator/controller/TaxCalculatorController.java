package com.seisma.taxcalculator.controller;

import com.seisma.taxcalculator.model.TaxCalculationRequest;
import com.seisma.taxcalculator.service.TaxCalculatorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@AllArgsConstructor
public class TaxCalculatorController {

    private TaxCalculatorService taxCalculatorService;

    @PostMapping("/tax/calculator")
    public ResponseEntity calculateTax(@RequestBody TaxCalculationRequest request) {
        return ResponseEntity.ok(taxCalculatorService.calculateTax(request.getSalary()));
    }
}
