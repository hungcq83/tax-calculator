package com.seisma.taxcalculator.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class TaxInfoConverter implements Converter<String, TaxInfo> {
  @Override
  public TaxInfo convert(String source) {
      if(source==null){
          return null;
      }
      String[] arr = source.split(",");
      return TaxInfo.builder()
              .start(Integer.valueOf(arr[0]))
              .rate(Double.valueOf(arr[1]))
              .build();
  }
}