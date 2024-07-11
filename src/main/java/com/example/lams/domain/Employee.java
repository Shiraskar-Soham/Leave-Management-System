package com.example.lams.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee extends BasicDetails{
    @Column(name ="authToken", unique = true, nullable = false)
    private String authToken;
    @Id
    @Column(name="empId", nullable = false, unique = true)
    private String empId;
    @Column(name="empName", nullable = false)
    private String empName;
    @Column(name="emailId")
    private String emailId;
    @Column(name="managerId", nullable = false)
    private String managerId;

    public Employee(Long dateCreated, Long dateModified, Boolean isDeleted) {
        super(dateCreated, dateModified, isDeleted);
    }

    public Employee() {}

    public Employee(Long dateCreated, Long dateModified, Boolean isDeleted, String authToken, String empId, String empName, String emailId, String managerId) {
        super(dateCreated, dateModified, isDeleted);
        this.authToken = authToken;
        this.empId = empId;
        this.empName = empName;
        this.emailId = emailId;
        this.managerId = managerId;
    }

    public Employee(String authToken, String empId, String empName, String emailId, String managerId) {
        this.authToken = authToken;
        this.empId = empId;
        this.empName = empName;
        this.emailId = emailId;
        this.managerId = managerId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}
