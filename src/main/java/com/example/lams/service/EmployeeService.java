package com.example.lams.service;

import com.example.lams.Repository.EmployeeRepository;
import com.example.lams.domain.Employee;
import com.example.lams.dtos.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    public List<Employee> getEmployeeByName(String name){
        if(ObjectUtils.isEmpty(name)){
            return null;
        }
        return employeeRepository.findByNameLike(name);
    }

    public boolean createEmployee(Employee employee){
        employeeRepository.save(employee);
        return true;
    }

    public Employee getEmployeeByEmpId(String empId){
        return employeeRepository.findByEmpId(empId);
    }

    public void deleteEmployeeByEmpId(String empId){
        Employee e = employeeRepository.findByEmpId(empId);
        e.setIsDeleted(true);
        e.setDateModified(System.currentTimeMillis());
        employeeRepository.save(e);
    }

    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee e = employeeRepository.findByEmpId(employeeDto.getEmpId());
        e.setDateModified(System.currentTimeMillis());
        e.setEmailId(employeeDto.getEmailId());
        e.setEmpName(employeeDto.getEmpName());
        e.setManagerId(employeeDto.getManagerId());
        employeeRepository.save(e);
        return employeeDto;
    }
}
