package com.example.lams.domain;

import com.example.lams.enums.LeaveStatus;
import jakarta.persistence.*;

import java.lang.reflect.GenericArrayType;

@Entity
@Table(name="leave_request")
public class LeaveApplication extends BasicDetails {
    @Id
    @Column(name="leaveId", nullable = false)
    private String leaveId;
    @Column(name="empId", nullable = false)
    private String empId;
    @Column(name="empName")
    private String empName;
    @Column(name= "reason")
    private String reason;
    @Column(name = "startDate", nullable = false)
    private Long startDate;
    @Column(name="endDate", nullable = false)
    private Long endDate;
    @Column(name="managerId", nullable = false)
    private String managerId;
    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    private LeaveStatus status;

    public LeaveApplication() {
    }

    public LeaveApplication(Long dateCreated, Long dateModified, Boolean isDeleted, String leaveId, String empId, String empName, String reason, Long startDate, Long endDate, String managerId, LeaveStatus status) {
        super(dateCreated, dateModified, isDeleted);
        this.leaveId = leaveId;
        this.empId = empId;
        this.empName = empName;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.managerId = managerId;
        this.status = status;
    }

    public LeaveApplication(String leaveId, String empId, String empName, String reason, Long startDate, Long endDate, String managerId, LeaveStatus status) {
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

    public static interface DateCreatedStep {
        DateModifiedStep withDateCreated(Long dateCreated);
    }

    public static interface DateModifiedStep {
        IsDeletedStep withDateModified(Long dateModified);
    }

    public static interface IsDeletedStep {
        LeaveIdStep withIsDeleted(Boolean isDeleted);
    }

    public static interface LeaveIdStep {
        EmpIdStep withLeaveId(String leaveId);
    }

    public static interface EmpIdStep {
        EmpNameStep withEmpId(String empId);
    }

    public static interface EmpNameStep {
        ReasonStep withEmpName(String empName);
    }

    public static interface ReasonStep {
        StartDateStep withReason(String reason);
    }

    public static interface StartDateStep {
        EndDateStep withStartDate(Long startDate);
    }

    public static interface EndDateStep {
        ManagerIdStep withEndDate(Long endDate);
    }

    public static interface ManagerIdStep {
        StatusStep withManagerId(String managerId);
    }

    public static interface StatusStep {
        BuildStep withStatus(LeaveStatus status);
    }

    public static interface BuildStep {
        LeaveApplication build();
    }


    public static class Builder implements DateCreatedStep, DateModifiedStep, IsDeletedStep, LeaveIdStep, EmpIdStep, EmpNameStep, ReasonStep, StartDateStep, EndDateStep, ManagerIdStep, StatusStep, BuildStep {
        private Long dateCreated;
        private Long dateModified;
        private Boolean isDeleted;
        private String leaveId;
        private String empId;
        private String empName;
        private String reason;
        private Long startDate;
        private Long endDate;
        private String managerId;
        private LeaveStatus status;

        private Builder() {
        }

        public static DateCreatedStep leaveApplication() {
            return new Builder();
        }

        @Override
        public DateModifiedStep withDateCreated(Long dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        @Override
        public IsDeletedStep withDateModified(Long dateModified) {
            this.dateModified = dateModified;
            return this;
        }

        @Override
        public LeaveIdStep withIsDeleted(Boolean isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        @Override
        public EmpIdStep withLeaveId(String leaveId) {
            this.leaveId = leaveId;
            return this;
        }

        @Override
        public EmpNameStep withEmpId(String empId) {
            this.empId = empId;
            return this;
        }

        @Override
        public ReasonStep withEmpName(String empName) {
            this.empName = empName;
            return this;
        }

        @Override
        public StartDateStep withReason(String reason) {
            this.reason = reason;
            return this;
        }

        @Override
        public EndDateStep withStartDate(Long startDate) {
            this.startDate = startDate;
            return this;
        }

        @Override
        public ManagerIdStep withEndDate(Long endDate) {
            this.endDate = endDate;
            return this;
        }

        @Override
        public StatusStep withManagerId(String managerId) {
            this.managerId = managerId;
            return this;
        }

        @Override
        public BuildStep withStatus(LeaveStatus status) {
            this.status = status;
            return this;
        }

        @Override
        public LeaveApplication build() {
            return new LeaveApplication(
                    this.dateCreated,
                    this.dateModified,
                    this.isDeleted,
                    this.leaveId,
                    this.empId,
                    this.empName,
                    this.reason,
                    this.startDate,
                    this.endDate,
                    this.managerId,
                    this.status
            );
        }
    }
}
