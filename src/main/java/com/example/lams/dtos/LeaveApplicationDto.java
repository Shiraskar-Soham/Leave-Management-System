package com.example.lams.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveApplicationDto {
    private String leaveId;
    private String reason;
    private Long startDate;
    private Long endDate;

    public static interface LeaveIdStep {
        ReasonStep withLeaveId(String leaveId);
    }

    public static interface ReasonStep {
        StartDateStep withReason(String reason);
    }

    public static interface StartDateStep {
        EndDateStep withStartDate(Long startDate);
    }

    public static interface EndDateStep {
        BuildStep withEndDate(Long endDate);
    }

    public static interface BuildStep {
        LeaveApplicationDto build();
    }


    public static class Builder implements LeaveIdStep, ReasonStep, StartDateStep, EndDateStep, BuildStep {
        private String leaveId;
        private String reason;
        private Long startDate;
        private Long endDate;

        private Builder() {
        }

        public static LeaveIdStep leaveApplicationDto() {
            return new Builder();
        }

        @Override
        public ReasonStep withLeaveId(String leaveId) {
            this.leaveId = leaveId;
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
        public BuildStep withEndDate(Long endDate) {
            this.endDate = endDate;
            return this;
        }

        @Override
        public LeaveApplicationDto build() {
            return new LeaveApplicationDto(
                    this.leaveId,
                    this.reason,
                    this.startDate,
                    this.endDate
            );
        }
    }
}
