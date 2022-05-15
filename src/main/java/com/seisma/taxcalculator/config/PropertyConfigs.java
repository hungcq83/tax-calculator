package com.seisma.taxcalculator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties
@Component
@Data
public class PropertyConfigs {

    private Map<Integer, TaxInfo> incomeTaxRates;
}
