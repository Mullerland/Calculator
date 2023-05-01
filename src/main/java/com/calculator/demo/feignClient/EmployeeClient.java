package com.calculator.demo.feignClient;

import com.calculator.demo.dto.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service", url = "http://localhost:8081/api/v1")
public interface EmployeeClient {
    @GetMapping("/emp/{id}")
    public Employee getEmployeeById(@PathVariable Long id);
}
