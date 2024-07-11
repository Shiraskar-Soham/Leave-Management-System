package com.example.lams.controller;


import com.example.lams.annotation.Authenticated;
import com.example.lams.domain.Employee;
import com.example.lams.dtos.EmployeeUpdateDto;
import com.example.lams.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/findByName")
    public List<Employee> getEmployeeByName(@RequestParam String empName) {
        return employeeService.getEmployeeByName(empName);
    }


    @PostMapping("/createEmployee")
    public String createEmployee(@RequestBody Employee employee) throws Exception {
        return employeeService.createEmployee(employee);
    }

    @Authenticated
    @GetMapping("/getEmployeeByEmpId")
    public Employee getByEmpId(@RequestParam String empId) throws Exception {
        return employeeService.getEmployeeByEmpId(empId);
    }

    @PostMapping("/deleteEmployeeById")
    public void deleteEmployeeByEmpId(@RequestParam String empId) throws Exception {
        employeeService.deleteEmployeeByEmpId(empId);
    }

    @PostMapping("/update")
    public EmployeeUpdateDto update(@RequestBody EmployeeUpdateDto employeeUpdateDto) throws Exception {
        return employeeService.updateEmployee(employeeUpdateDto);
    }
}
