package com.calculator.demo.controller;

import com.calculator.demo.entity.VacationCalculatorIncomeEntity;
import com.calculator.demo.service.VacationCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class VacationCalculatorController {

    private final VacationCalculatorService vacationCalculatorService;

    @PostMapping("/calculate")
    public VacationCalculatorIncomeEntity calculateVacation(
            @RequestBody VacationCalculatorIncomeEntity vacationCalculatorIncomeEntity) {
        return vacationCalculatorService.VacationCalculator(vacationCalculatorIncomeEntity);
    }


}