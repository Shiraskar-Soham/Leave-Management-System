package com.example.lams.dtos;

import com.example.lams.enums.LeaveStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveApplicationDto {
    private String leaveId;
    private String empId;
    private String empName;
    private String reason;
    private Long startDate;
    private Long endDate;
    private LeaveStatus status;

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
        StatusStep withEndDate(Long endDate);
    }

    public static interface StatusStep {
        BuildStep withStatus(LeaveStatus status);
    }

    public static interface BuildStep {
        LeaveApplicationDto build();
    }


    public static class Builder implements LeaveIdStep, EmpIdStep, EmpNameStep, ReasonStep, StartDateStep, EndDateStep, StatusStep, BuildStep {
        private String leaveId;
        private String empId;
        private String empName;
        private String reason;
        private Long startDate;
        private Long endDate;
        private LeaveStatus status;

        private Builder() {
        }

        public static LeaveIdStep leaveApplicationDto() {
            return new Builder();
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
        public StatusStep withEndDate(Long endDate) {
            this.endDate = endDate;
            return this;
        }

        @Override
        public BuildStep withStatus(LeaveStatus status) {
            this.status = status;
            return this;
        }

        @Override
        public LeaveApplicationDto build() {
            return new LeaveApplicationDto(
                    this.leaveId,
                    this.empId,
                    this.empName,
                    this.reason,
                    this.startDate,
                    this.endDate,
                    this.status
            );
        }
    }
}
