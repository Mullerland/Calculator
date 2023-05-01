package com.calculator.demo.entity;

import com.calculator.demo.dto.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vacation_calculator_income")
public class VacationCalculatorIncomeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    @Schema(description = "Начало расчетного периода")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "yyyy-MM-dd")
    private LocalDate startBillingPeriod;
    @Column
    @Schema(description = "Конец расчетного периода")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "yyyy-MM-dd")
    private LocalDate endBillingPeriod;
    @Column
    @Schema(description = "Не учетные дни расчетного периода")
    private int unaccountedDaysForTheBillingPeriod;
    @Column
    @Schema(description = "Общий доход за расчетный период")
    private int incomeForTheBillingPeriod;
    @Column
    @Schema(description = "Начало отпуска")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @Column
    @Schema(description = "Конец отпуска")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @Column
    @Schema(description = "Не учетные дни отпуска")
    private int unaccountedDaysForTheVacation;
    @Column
    @Schema(description = "Отпускные")
    private double holidayPay;
    @Column
    @Schema(description = "Id работника, к которому нужно рассчитать отпускные")
    private Long emKey;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id", referencedColumnName = "id")
    Employee employee;
}
