package com.seisma.taxcalculator.service;

import com.seisma.taxcalculator.entity.TaxRateEntity;
import com.seisma.taxcalculator.model.Employee;
import com.seisma.taxcalculator.model.IncomeResponse;
import com.seisma.taxcalculator.model.MonthInfo;
import com.seisma.taxcalculator.repository.TaxRateRepository;
import com.seisma.taxcalculator.util.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaxCalculatorService {

    private double superRate;

    @Autowired
    private TaxRateRepository taxRateRepository;

    private List<TaxRateEntity> sortedTaxRates;

    @PostConstruct
    private List<TaxRateEntity> getSortedTaxRate() {
        if (sortedTaxRates == null) {
            sortedTaxRates = taxRateRepository.findAll();
            Collections.sort(sortedTaxRates, (obj1, obj2) -> {
                return obj1.getStartFrom() > obj2.getStartFrom() ? -1 : 1;
            });
        }
        return sortedTaxRates;
    }

    public List<IncomeResponse> calculateIncomes(List<Employee> employees) {
        List<IncomeResponse> outcomeOutputs = employees.stream()
                .map(employee -> calculateTax(employee))
                .collect(Collectors.toList());

        return outcomeOutputs;
    }

    private IncomeResponse calculateTax(Employee employee) {
        TaxRateEntity appliedTaxRate = getSortedTaxRate().stream()
                .filter(taxRate -> employee.getAnnualSalary() >= taxRate.getStartFrom())
                .findFirst()
                .get();

        int baseTaxAmount = appliedTaxRate.getBaseTaxAmount();

        float taxPerDollar = appliedTaxRate.getTaxPerDollar();

        int grossIncome = Math.round(employee.getAnnualSalary()/12);

        int incomeTax = Math.round(
                (baseTaxAmount + taxPerDollar * (employee.getAnnualSalary() - appliedTaxRate.getStartFrom() + 1)) / 12
        );

        int netIncome = grossIncome - incomeTax;

        int superAnnuation = Math.round(grossIncome * employee.getSuperRate());

        MonthInfo monthInfo = CalendarUtil.getMonthInfo(employee.getPaymentMonth());

        return IncomeResponse.builder()
                .employee(employee)
                .fromDate(monthInfo.getFirstDay())
                .toDate(monthInfo.getLastDay())
                .grossIncome(grossIncome)
                .incomeTax(incomeTax)
                .netIncome(netIncome)
                .superAnnuation(superAnnuation)
                .build();

    }


}

