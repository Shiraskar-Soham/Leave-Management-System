package com.example.lams.converter;

import com.example.lams.domain.LeaveApplication;
import com.example.lams.domain.LeaveApplicationIndex;
import org.springframework.beans.factory.annotation.Autowired;


public class EsToMysql {

    @Autowired
    private LeaveApplicationIndex leaveApplicationIndex;
    @Autowired
    private LeaveApplication leaveApplication;

    public LeaveApplicationIndex converterIndex (LeaveApplication leaveApplication){
        leaveApplicationIndex.setLeaveId(this.leaveApplication.getLeaveId());
        leaveApplicationIndex.setEmpId(this.leaveApplication.getEmpId());
        leaveApplicationIndex.setStartDate(this.leaveApplication.getStartDate());
        leaveApplicationIndex.setEndDate(this.leaveApplication.getEndDate());


        return leaveApplicationIndex;
    }
}
