package com.calculator.demo.service;

import com.calculator.demo.dto.Employee;
import com.calculator.demo.entity.VacationCalculatorIncomeEntity;
import com.calculator.demo.feignClient.EmployeeClient;
import com.calculator.demo.repository.VacationCalculatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class VacationCalculatorService {
    private final VacationCalculatorRepository vacationCalculatorRepository;
    private final EmployeeClient employeeClient;
    public VacationCalculatorIncomeEntity VacationCalculator(VacationCalculatorIncomeEntity vacationCalculatorIncomeEntity) {

        Employee employee = employeeClient.getEmployeeById(vacationCalculatorIncomeEntity.getEmKey());
        vacationCalculatorIncomeEntity.setEmployee(employee);
        //Среднедневной заработок — это доход за расчетный период, поделенный на количество отработанных дней
        // за исключением неучтенных дней
        int averageDailyEarnings = vacationCalculatorIncomeEntity.getIncomeForTheBillingPeriod() /
                (int) ChronoUnit.DAYS.between(vacationCalculatorIncomeEntity.getStartBillingPeriod(),
                        vacationCalculatorIncomeEntity.getEndBillingPeriod().
                                minusDays(vacationCalculatorIncomeEntity.getUnaccountedDaysForTheBillingPeriod()));
        // Период отпуска
        int vacationPeriod = (int) ChronoUnit.DAYS.between(vacationCalculatorIncomeEntity.getStartDate(),
                vacationCalculatorIncomeEntity.getEndDate());
        //Расчет налога на добавленную стоимость (value added tax)
        float vat = (float) ((averageDailyEarnings * vacationPeriod) *0.13);
        //Отпускные = среднедневной заработок × количество дней отпуска - 13% НДФЛ
        vacationCalculatorIncomeEntity.setHolidayPay(Math.round(averageDailyEarnings * vacationPeriod - vat));
        return vacationCalculatorRepository.save(vacationCalculatorIncomeEntity);
    }


}
