package com.example.lams.controller;

import com.example.lams.domain.LeaveApplication;
import com.example.lams.dtos.LeaveApplicationUpdateDto;
import com.example.lams.enums.LeaveStatus;
import com.example.lams.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/leave")
public class LeaveController {
    @Autowired
    private LeaveApplicationService leaveApplicationService;

    @PostMapping("/apply")
    public boolean createLeaveApplication(@RequestBody LeaveApplication leaveApplication) throws Exception {
        return leaveApplicationService.createLeaveApplication(leaveApplication);
    }

    @GetMapping("/getLeaveDetails")
    public LeaveApplication getLeaveDetails(@RequestParam String leaveId) throws Exception {
        return leaveApplicationService.getLeaveApplication(leaveId);
    }

    @GetMapping("/checkStatusById")
    public List<LeaveApplication> getLeavesOfAnEmployee(@RequestParam String empId) throws Exception {
        return leaveApplicationService.getLeavesOfAnEmployee(empId);
    }

    @PostMapping("/deleteMyLeave")
    public void deleteMyLeave(@RequestParam String leaveId) throws Exception {
        leaveApplicationService.deleteLeave(leaveId);
    }

    @PostMapping("/update")
    public LeaveApplicationUpdateDto update(@RequestBody LeaveApplicationUpdateDto leaveApplicationUpdateDto) throws Exception {
        return leaveApplicationService.updateLeaveApplication(leaveApplicationUpdateDto);
    }

    @PostMapping("/manageLeave")
    public LeaveApplication manageLeave(@RequestParam String leaveId, @RequestParam LeaveStatus status, @RequestParam String loggedInAccountId) throws Exception {
        return leaveApplicationService.approvalAndRejectionByManager(leaveId, status, loggedInAccountId);
    }
}
