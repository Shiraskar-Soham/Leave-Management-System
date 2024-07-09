package com.example.lams.convertors;

import com.example.lams.domain.LeaveApplication;
import com.example.lams.dtos.LeaveApplicationDto;
import org.springframework.stereotype.Component;

@Component
public class LeaveApplicationDtoConvertor {

    public LeaveApplicationDto convertTo (LeaveApplication leaveApplication) {
        return LeaveApplicationDto.Builder.leaveApplicationDto()
                .withLeaveId(leaveApplication.getLeaveId())
                .withEmpId(leaveApplication.getEmpId())
                .withEmpName(leaveApplication.getEmpName())
                .withReason(leaveApplication.getReason())
                .withStartDate(leaveApplication.getStartDate())
                .withEndDate(leaveApplication.getEndDate())
                .withStatus(leaveApplication.getStatus())
                .build();
    }

}
