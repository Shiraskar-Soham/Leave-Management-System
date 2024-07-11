package com.example.lams.service;

import com.example.lams.Repository.EmployeeRepository;
import com.example.lams.domain.Employee;
import com.example.lams.dtos.EmployeeUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    public List<Employee> getEmployeeByName(String name) {
        if (ObjectUtils.isEmpty(name)) {
            return null;
        }
        return employeeRepository.findByNameLike(name);
    }

    public String createEmployee(Employee employee) throws Exception {
        if (ObjectUtils.isEmpty(employee)) {
            throw new Exception("Employee cannot be blank");
        }
        employee.setDateModified(System.currentTimeMillis());
        employee.setDateCreated(System.currentTimeMillis());
        employee.setIsDeleted(false);
        String authToken = employee.getEmpId() + "_" + employee.getEmailId();
        employee.setAuthToken(authToken);
        employeeRepository.save(employee);
        return employee.getAuthToken();
    }

    public Employee getEmployeeByEmpId(String empId) throws Exception {
        if (ObjectUtils.isEmpty(empId)) {
            throw new Exception("EmpId cannot be blank");
        }
        return employeeRepository.findByEmpId(empId);
    }

    public void deleteEmployeeByEmpId(String empId) throws Exception {
        if (ObjectUtils.isEmpty(empId)) {
            throw new Exception("EmpId cannot be blank");
        }
        Employee e = employeeRepository.findByEmpId(empId);
        if (ObjectUtils.isEmpty(empId)) {
            throw new Exception("No Employee Found for empID = " + empId);
        }
        if (e.getIsDeleted()) {
            throw new Exception("Employee is already deleted");
        }
        e.setIsDeleted(true);
        e.setDateModified(System.currentTimeMillis());
        employeeRepository.save(e);
    }

    public EmployeeUpdateDto updateEmployee(EmployeeUpdateDto employeeUpdateDto) throws Exception {
        if (ObjectUtils.isEmpty(employeeUpdateDto)) {
            throw new Exception("EmployeeDTO cannot be empty");
        }
        Employee e = employeeRepository.findByEmpId(employeeUpdateDto.getEmpId());
        if (ObjectUtils.isEmpty(e)) {
            throw new Exception("No Employee Found");
        }
        e.setDateModified(System.currentTimeMillis());
        e.setEmailId(employeeUpdateDto.getEmailId());
        e.setEmpName(employeeUpdateDto.getEmpName());
        e.setManagerId(employeeUpdateDto.getManagerId());
        employeeRepository.save(e);
        return employeeUpdateDto;
    }

    public boolean validateBasicAuthentication(String basicAuthHeaderValue) throws Exception {
        if(ObjectUtils.isEmpty(basicAuthHeaderValue)) {
            throw new Exception("Token Cannot Be Empty");
        }
        Employee e = employeeRepository.findByAuthToken(basicAuthHeaderValue);
        return !ObjectUtils.isEmpty(e);
    }
}
