package com.calculator.demo.repository;

import com.calculator.demo.entity.VacationCalculatorIncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationCalculatorRepository extends JpaRepository<VacationCalculatorIncomeEntity, Long> {
}
