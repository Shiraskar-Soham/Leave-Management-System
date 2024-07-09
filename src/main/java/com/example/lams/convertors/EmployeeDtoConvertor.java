package com.example.lams.convertors;

import com.example.lams.domain.Employee;
import com.example.lams.dtos.EmployeeDto;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDtoConvertor {

    public EmployeeDto convertTo (Employee employee) {
        return EmployeeDto.Builder.employeeDto()
                .withEmpId(employee.getEmpId())
                .withEmpName(employee.getEmpName())
                .withManagerId(employee.getManagerId())
                .withEmailId(employee.getEmailId())
                .build();
    }

}
