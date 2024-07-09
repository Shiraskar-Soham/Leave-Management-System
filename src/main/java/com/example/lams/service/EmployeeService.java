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

    public boolean createEmployee(Employee employee) throws Exception {
        if(ObjectUtils.isEmpty(employee)) {
            throw new Exception("Employee cannot be blank");
        }
        employeeRepository.save(employee);
        return true;
    }

    public Employee getEmployeeByEmpId(String empId){
        //check for null empId
        return employeeRepository.findByEmpId(empId);
    }

    public void deleteEmployeeByEmpId(String empId){
        //check for null empId
        Employee e = employeeRepository.findByEmpId(empId);
        //Check if employee not found
        e.setIsDeleted(true);
        e.setDateModified(System.currentTimeMillis());
        employeeRepository.save(e);
    }

    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        //check for empty dto
        Employee e = employeeRepository.findByEmpId(employeeDto.getEmpId());
        //check if employee not found
        e.setDateModified(System.currentTimeMillis());
        e.setEmailId(employeeDto.getEmailId());
        e.setEmpName(employeeDto.getEmpName());
        e.setManagerId(employeeDto.getManagerId());
        employeeRepository.save(e);
        return employeeDto;
    }
}
