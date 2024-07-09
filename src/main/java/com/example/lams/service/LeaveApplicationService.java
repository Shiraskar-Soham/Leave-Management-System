package com.example.lams.service;

import com.example.lams.Repository.LeaveApplicationRepository;
import com.example.lams.domain.LeaveApplication;
import com.example.lams.dtos.LeaveApplicationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveApplicationService {
    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;

    public boolean createLeaveApplication(LeaveApplication leaveApplication) {
        leaveApplicationRepository.save(leaveApplication);
        return true;
    }

    public List<LeaveApplication> getLeavesOfAnEmployee(String empId){
        return leaveApplicationRepository.findByEmpId(empId);
    }

    public LeaveApplication getLeaveApplication(String leaveId) {
        return leaveApplicationRepository.findByLeaveId(leaveId);
    }

    public void deleteLeave(String leaveId) {
        LeaveApplication l = leaveApplicationRepository.findByLeaveId(leaveId);
        l.setIsDeleted(true);
        l.setDateModified(System.currentTimeMillis());
        leaveApplicationRepository.save(l);
    }

    public LeaveApplicationDto updateLeaveApplication(LeaveApplicationDto leaveApplicationDto) {
        LeaveApplication l = leaveApplicationRepository.findByLeaveId(leaveApplicationDto.getLeaveId());
        l.setDateModified(System.currentTimeMillis());
        l.setReason(leaveApplicationDto.getReason());
        l.setStartDate(leaveApplicationDto.getStartDate());
        l.setEndDate(leaveApplicationDto.getEndDate());
        leaveApplicationRepository.save(l);
        return leaveApplicationDto;
    }
}
