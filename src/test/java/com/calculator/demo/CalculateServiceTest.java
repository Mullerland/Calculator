package com.calculator.demo;

import com.calculator.demo.entity.VacationCalculatorIncomeEntity;
import com.calculator.demo.repository.VacationCalculatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CalculateServiceTest {


    @Mock
    private VacationCalculatorRepository vacationCalculatorRepository;

    private VacationCalculatorIncomeEntity vacationCalculatorIncomeEntity;


    @BeforeEach
    public void init() {
        vacationCalculatorIncomeEntity = vacationCalculatorIncomeEntity.builder()
                .id(1L)
                .emKey(1L)
                .startBillingPeriod(LocalDate.parse("2021-11-01"))
                .endBillingPeriod(LocalDate.parse("2022-10-31"))
                .unaccountedDaysForTheBillingPeriod(5)
                .incomeForTheBillingPeriod(600000)
                .startDate(LocalDate.parse("2022-11-01"))
                .endDate(LocalDate.parse("2022-11-11"))
                .unaccountedDaysForTheVacation(3)
                .build();

    }

    @Test
    public void VacationCalculator() {
        int averageDailyEarnings = vacationCalculatorIncomeEntity.getIncomeForTheBillingPeriod() /
                (int) ChronoUnit.DAYS.between(vacationCalculatorIncomeEntity.getStartBillingPeriod(),
                        vacationCalculatorIncomeEntity.getEndBillingPeriod().
                                minusDays(vacationCalculatorIncomeEntity.getUnaccountedDaysForTheBillingPeriod()));
        int vacationPeriod = (int) ChronoUnit.DAYS.between(vacationCalculatorIncomeEntity.getStartDate(),
                vacationCalculatorIncomeEntity.getEndDate());
        float vat = (float) ((averageDailyEarnings * vacationPeriod) * 0.13);
        vacationCalculatorIncomeEntity.setHolidayPay(Math.round(averageDailyEarnings * vacationPeriod - vat));
        when(vacationCalculatorRepository.save(vacationCalculatorIncomeEntity)).thenReturn(vacationCalculatorIncomeEntity);
        vacationCalculatorRepository.save(vacationCalculatorIncomeEntity);

        assertNotNull(vacationCalculatorIncomeEntity);
        assertEquals(1L, vacationCalculatorIncomeEntity.getId());
        assertEquals(14538.0, vacationCalculatorIncomeEntity.getHolidayPay());


    }
}

