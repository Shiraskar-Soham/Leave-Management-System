package com.example.lams.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdateDto {
    private String empId;
    private String empName;
    private String managerId;
    private String emailId;

    public static interface EmpIdStep {
        EmpNameStep withEmpId(String empId);
    }

    public static interface EmpNameStep {
        ManagerIdStep withEmpName(String empName);
    }

    public static interface ManagerIdStep {
        EmailIdStep withManagerId(String managerId);
    }

    public static interface EmailIdStep {
        BuildStep withEmailId(String emailId);
    }

    public static interface BuildStep {
        EmployeeUpdateDto build();
    }


    public static class Builder implements EmpIdStep, EmpNameStep, ManagerIdStep, EmailIdStep, BuildStep {
        private String empId;
        private String empName;
        private String managerId;
        private String emailId;

        private Builder() {
        }

        public static EmpIdStep employeeDto() {
            return new Builder();
        }

        @Override
        public EmpNameStep withEmpId(String empId) {
            this.empId = empId;
            return this;
        }

        @Override
        public ManagerIdStep withEmpName(String empName) {
            this.empName = empName;
            return this;
        }

        @Override
        public EmailIdStep withManagerId(String managerId) {
            this.managerId = managerId;
            return this;
        }

        @Override
        public BuildStep withEmailId(String emailId) {
            this.emailId = emailId;
            return this;
        }

        @Override
        public EmployeeUpdateDto build() {
            return new EmployeeUpdateDto(
                    this.empId,
                    this.empName,
                    this.managerId,
                    this.emailId
            );
        }
    }
}
