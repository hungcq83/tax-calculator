package com.seisma.taxcalculator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tax_rates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxRateEntity {
    @Id
    private Integer id;
    private int startFrom;
    private float taxPerDollar;
    private int baseTaxAmount;
}
