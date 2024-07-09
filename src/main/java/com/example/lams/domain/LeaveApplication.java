package com.example.lams.domain;

import com.example.lams.enums.LeaveStatus;
import jakarta.persistence.*;
import lombok.Builder;

@Builder
@Entity
@Table(name="leave")
public class LeaveApplication extends BasicDetails {
    @Id
    @Column(name="leaveId")
    private String leaveId;
    @Column(name="empId")
    private String empId;
    @Column(name="empName")
    private String empName;
    @Column(name= "reason")
    private String reason;
    @Column(name = "startDate")
    private Long startDate;
    @Column(name="endDate")
    private Long endDate;
    @Column(name="managerId")
    private String managerId;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private LeaveStatus status;

    public LeaveApplication(Long dateCreated, String creatorId, Long dateModified, String modifierId, Boolean isDeleted, String leaveId, String empId, String empName, String reason, Long startDate, Long endDate, String managerId, LeaveStatus status) {
        super(dateCreated, creatorId, dateModified, modifierId, isDeleted);
        this.leaveId = leaveId;
        this.empId = empId;
        this.empName = empName;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.managerId = managerId;
        this.status = status;
    }

    public String getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(String leaveId) {
        this.leaveId = leaveId;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }
}
