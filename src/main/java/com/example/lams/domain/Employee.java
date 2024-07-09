package com.example.lams.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "employee")
public class Employee extends BasicDetails{
    @Id
    @Column(name="empId")
    private String empId;
    @Column(name="empName")
    private String empName;
    @Column(name="emailId")
    private String emailId;
    @Column(name="managerId")
    private String managerId;

    public Employee(Long dateCreated, String creatorId, Long dateModified, String modifierId, Boolean isDeleted, String empId, String empName, String emailId, String managerId) {
        super(dateCreated, creatorId, dateModified, modifierId, isDeleted);
        this.empId = empId;
        this.empName = empName;
        this.emailId = emailId;
        this.managerId = managerId;
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
