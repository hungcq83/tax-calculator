package com.seisma.taxcalculator.controller;

import com.seisma.taxcalculator.model.IncomeInput;
import com.seisma.taxcalculator.service.TaxCalculatorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@AllArgsConstructor
@Validated
public class TaxCalculatorController {

    private TaxCalculatorService taxCalculatorService;

    @PostMapping("/tax/calculator")
    public ResponseEntity calculateTax(@RequestBody @NotEmpty List<@Valid IncomeInput> inputs) {
        return ResponseEntity.ok(taxCalculatorService.calculateIncomes(inputs));
    }
}
