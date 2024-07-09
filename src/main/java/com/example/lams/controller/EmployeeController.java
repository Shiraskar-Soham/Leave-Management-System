package com.example.lams.controller;


import com.example.lams.domain.Employee;
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
    public boolean createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/getEmployeeByEmpId")
    public Employee getByEmpId(@RequestParam String empId) {
        return employeeService.getEmployeeByEmpId(empId);
    }

    @DeleteMapping("/deleteEmployeeById")
    public void deleteEmployeeByEmpId(@RequestParam String empId){
        employeeService.deleteEmployeeByEmpId(empId);
    }

}
