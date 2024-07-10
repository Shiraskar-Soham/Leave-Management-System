package com.example.lams.service;

import com.example.lams.Repository.LeaveApplicationRepository;
import com.example.lams.domain.LeaveApplication;
import com.example.lams.dtos.LeaveApplicationDto;
import com.example.lams.enums.LeaveStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

@Service
public class LeaveApplicationService {
    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;

    public boolean createLeaveApplication(LeaveApplication leaveApplication) {
        //check for empty leave
        leaveApplication.setDateCreated(System.currentTimeMillis());
        leaveApplication.setDateModified(System.currentTimeMillis());
        leaveApplication.setStatus(LeaveStatus.PENDING);
        leaveApplicationRepository.save(leaveApplication);
        return true;
    }

    public List<LeaveApplication> getLeavesOfAnEmployee(String empId){
        //check for empty empId
        return leaveApplicationRepository.findByEmpId(empId);
    }

    public LeaveApplication getLeaveApplication(String leaveId) {
        //check for null leaveId
        return leaveApplicationRepository.findByLeaveId(leaveId);
    }

    public void deleteLeave(String leaveId) {
        //check for null leaveId
        LeaveApplication l = leaveApplicationRepository.findByLeaveId(leaveId);
        // check for leave not found
        l.setIsDeleted(true);
        l.setDateModified(System.currentTimeMillis());
        leaveApplicationRepository.save(l);
    }

    public LeaveApplicationDto updateLeaveApplication(LeaveApplicationDto leaveApplicationDto) {
        //check for empty dto
        LeaveApplication l = leaveApplicationRepository.findByLeaveId(leaveApplicationDto.getLeaveId());
        //check for leave not found
        l.setDateModified(System.currentTimeMillis());
        l.setReason(leaveApplicationDto.getReason());
        l.setStartDate(leaveApplicationDto.getStartDate());
        l.setEndDate(leaveApplicationDto.getEndDate());
        leaveApplicationRepository.save(l);
        return leaveApplicationDto;
    }

    public LeaveApplication approvalAndRejectionByManager(String leaveId, LeaveStatus status, String loggedInAccountId) throws Exception {
        if (leaveId == null) {
            throw new Exception("Leave ID cannot be blank");
        }
        LeaveApplication leaveApplication = leaveApplicationRepository.findByLeaveId(leaveId);
        if (ObjectUtils.isEmpty(leaveApplication)) {
            throw new Exception("Leave not found for leave Id: " + leaveId);
        }
        if (!Objects.equals(loggedInAccountId, leaveApplication.getManagerId())) {
            throw new Exception("Leave " + leaveId + " can only be approved by employee's manager with manager empCode: " + leaveApplication.getManagerId());
        }
        if (status != LeaveStatus.PENDING) {
            throw new Exception("Leave is already marked approved or rejected");
        }
        leaveApplication.setStatus(status);
        leaveApplication.setDateModified(System.currentTimeMillis());
        return leaveApplicationRepository.save(leaveApplication);
    }
}
