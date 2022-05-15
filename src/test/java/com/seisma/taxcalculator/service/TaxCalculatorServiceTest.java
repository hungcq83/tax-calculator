package com.seisma.taxcalculator.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seisma.taxcalculator.config.PropertyConfigs;
import com.seisma.taxcalculator.config.TaxInfo;
import com.seisma.taxcalculator.model.IncomeInput;
import com.seisma.taxcalculator.model.IncomeOutput;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
public class TaxCalculatorServiceTest {

    private static List<IncomeInput> inputs;

    private static ObjectMapper mapper = new ObjectMapper();

    @Mock
    private PropertyConfigs propertyConfigs;

    @InjectMocks
    private TaxCalculatorService taxCalculatorService;

    @BeforeAll
    static void init() throws IOException {
        InputStream stream = TaxCalculatorServiceTest.class.getResourceAsStream("/inputs.json");
        inputs = mapper.readValue(stream, new TypeReference<List<IncomeInput>>(){});
    }

    @BeforeEach
    void setup() {
        when(propertyConfigs.getIncomeTaxRates()).thenReturn(getTaxRates());
    }

    Map<Integer, TaxInfo> getTaxRates() {
        var taxRates = new LinkedHashMap<Integer, TaxInfo>();
        taxRates.put(180001,
                TaxInfo.builder()
                    .start(54232)
                    .rate(0.45)
                    .build()
        );
        taxRates.put(87001,
                TaxInfo.builder()
                        .start(19822)
                        .rate(0.37)
                        .build()
        );
        taxRates.put(37001,
                TaxInfo.builder()
                        .start(3572)
                        .rate(0.325)
                        .build()
        );
        taxRates.put(18201,
                TaxInfo.builder()
                        .start(0)
                        .rate(0.19)
                        .build()
        );
        taxRates.put(0,
                TaxInfo.builder()
                        .start(0)
                        .rate(0)
                        .build()
        );

        return taxRates;
    }

    @Test
    void testZeroTax() {
        IncomeOutput incomeOutput;

        inputs.get(0).setAnnualSalary(18200);
        incomeOutput = taxCalculatorService.calculateIncomes(inputs).get(0);
        assertEquals(incomeOutput.getIncomeTax(), 0);

        inputs.get(0).setAnnualSalary(0);
        incomeOutput = taxCalculatorService.calculateIncomes(inputs).get(0);
        assertEquals(incomeOutput.getIncomeTax(), 0);
    }

    @Test
    void testFirstRangeTax() {
        IncomeOutput incomeOutput;

        inputs.get(0).setAnnualSalary(18200);
        incomeOutput = taxCalculatorService.calculateIncomes(inputs).get(0);
        assertEquals(0, incomeOutput.getIncomeTax());

        inputs.get(0).setAnnualSalary(37000);
        incomeOutput = taxCalculatorService.calculateIncomes(inputs).get(0);
        assertEquals(298, incomeOutput.getIncomeTax());
    }
}
