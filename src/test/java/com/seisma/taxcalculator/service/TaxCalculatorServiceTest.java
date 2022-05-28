package com.seisma.taxcalculator.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seisma.taxcalculator.entity.TaxRateEntity;
import com.seisma.taxcalculator.model.Employee;
import com.seisma.taxcalculator.model.IncomeResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.seisma.taxcalculator.repository.TaxRateRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class TaxCalculatorServiceTest {

    private static List<Employee> inputs;

    private static ObjectMapper mapper = new ObjectMapper();

    @Mock
    private TaxRateRepository taxRateRepository;

    @InjectMocks
    private TaxCalculatorService taxCalculatorService;

    @BeforeAll
    static void init() throws IOException {
        InputStream stream = TaxCalculatorServiceTest.class.getResourceAsStream("/inputs.json");
        inputs = mapper.readValue(stream, new TypeReference<List<Employee>>(){});
    }

    @BeforeEach
    void setup() {
        when(taxRateRepository.findAll()).thenReturn(
            Arrays.asList(
                    new TaxRateEntity(0, 0, 0, 0),
                    new TaxRateEntity(1, 18201, 0.19f, 0),
                    new TaxRateEntity(2, 37001, 0.325f, 3572),
                    new TaxRateEntity(3, 87001, 0.37f, 19822),
                    new TaxRateEntity(4, 180001, 0.45f, 54232)
            )
        );
    }

    @Test
    void testZeroTax() {
        IncomeResponse incomeResponse;

        inputs.get(0).setAnnualSalary(18200);
        incomeResponse = taxCalculatorService.calculateIncomes(inputs).get(0);
        assertEquals(incomeResponse.getIncomeTax(), 0);

        inputs.get(0).setAnnualSalary(0);
        incomeResponse = taxCalculatorService.calculateIncomes(inputs).get(0);
        assertEquals(incomeResponse.getIncomeTax(), 0);

        assertEquals(incomeResponse.getFromDate(), "1 March");
        assertEquals(incomeResponse.getToDate(), "31 March");
    }

    @Test
    void testFirstRangeTax() {
        IncomeResponse incomeResponse;

        inputs.get(0).setAnnualSalary(18200);
        incomeResponse = taxCalculatorService.calculateIncomes(inputs).get(0);
        assertEquals(0, incomeResponse.getIncomeTax());

        inputs.get(0).setAnnualSalary(37000);
        incomeResponse = taxCalculatorService.calculateIncomes(inputs).get(0);
        assertEquals(298, incomeResponse.getIncomeTax());
    }
}
