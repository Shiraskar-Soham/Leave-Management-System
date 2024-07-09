package com.example.lams.controller;

import com.example.lams.domain.LeaveApplication;
import com.example.lams.dtos.LeaveApplicationDto;
import com.example.lams.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class LeaveController {
    @Autowired
    private LeaveApplicationService leaveApplicationService;

    @PostMapping("/apply")
    public boolean createLeaveApplication(@RequestBody LeaveApplication leaveApplication){
        return leaveApplicationService.createLeaveApplication(leaveApplication);
    }

    @GetMapping("/getLeaveDetails")
    public LeaveApplication getLeaveDetails(@RequestParam String leaveId){
        return leaveApplicationService.getLeaveApplication(leaveId);
    }

    @GetMapping("/checkStatusById")
    public List<LeaveApplication> getLeavesOfAnEmployee(@RequestParam String empId){
        return leaveApplicationService.getLeavesOfAnEmployee(empId);
    }

    @DeleteMapping("/deleteMyLeave")
    public void deleteMyLeave(@RequestParam String leaveId){
        leaveApplicationService.deleteLeave(leaveId);
    }

    @PostMapping("/update")
    public LeaveApplicationDto update(@RequestBody LeaveApplicationDto leaveApplicationDto){
        return leaveApplicationService.updateLeaveApplication(leaveApplicationDto);
    }
}
