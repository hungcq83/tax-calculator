package com.seisma.taxcalculator.service;

import com.seisma.taxcalculator.config.PropertyConfigs;
import com.seisma.taxcalculator.config.TaxInfo;
import com.seisma.taxcalculator.model.IncomeInput;
import com.seisma.taxcalculator.model.IncomeOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaxCalculatorService {

    private double superRate;

    @Autowired
    private PropertyConfigs propertyConfigs;

    public List<IncomeOutput> calculateIncomes(List<IncomeInput> incomeInputs) {
        List<IncomeOutput> outcomeOutputs = incomeInputs.stream()
                .map(incomeInput -> calculateTax(incomeInput))
                .collect(Collectors.toList());

        return outcomeOutputs;
    }

    private IncomeOutput calculateTax(IncomeInput input) {
        Map.Entry<Integer, TaxInfo> taxRate = propertyConfigs.getIncomeTaxRates().entrySet().stream()
                .filter(entry -> input.getAnnualSalary() >= entry.getKey())
                .findFirst()
                .get();

        int startAmount = taxRate.getValue().getStart();
        double rate = taxRate.getValue().getRate();

        int grossIncome = Math.round(input.getAnnualSalary()/12);

        int incomeTax = (int) Math.round(
                (startAmount + rate * (input.getAnnualSalary() - taxRate.getKey() + 1)) / 12
        );

        int netIncome = grossIncome - incomeTax;

        int superAmount = grossIncome * input.getSuperRate() / 100;

        return IncomeOutput.builder()
                .fullName(String.format("%s %s", input.getFirstName(), input.getLastName()))
                .paymentPeriod(input.getPaymentPeriod())
                .grossIncome(grossIncome)
                .incomeTax(incomeTax)
                .netIncome(netIncome)
                .superAmount(superAmount)
                .build();

    }
}
