package com.seisma.taxcalculator.repository;

import com.seisma.taxcalculator.entity.TaxRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxRateRepository extends JpaRepository<TaxRateEntity, Integer> {
    List<TaxRateEntity> findAll();
}
