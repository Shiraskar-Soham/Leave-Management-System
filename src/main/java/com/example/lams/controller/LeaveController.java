package com.example.lams.controller;

import com.example.lams.annotation.Authenticated;
import com.example.lams.domain.LeaveApplication;
import com.example.lams.domain.LeaveApplicationIndex;
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

    @Authenticated
    @PostMapping("/apply")
    public boolean createLeaveApplication(@RequestBody LeaveApplication leaveApplication) throws Exception {
        return leaveApplicationService.createLeaveApplication(leaveApplication);
    }

    @GetMapping("/getLeaveDetails")
    public LeaveApplication getLeaveDetails(@RequestParam String leaveId) throws Exception {
        return leaveApplicationService.getLeaveApplication(leaveId);
    }

    @GetMapping("/getAllLeaves")
    public List<LeaveApplicationIndex> getLeavesOfAnEmployee() throws Exception {
        return leaveApplicationService.getAllLeaves();
    }

    @GetMapping("/checkStatusById")
    public List<LeaveApplication> getLeavesOfAnEmployee(@RequestParam String empId) throws Exception {
        return leaveApplicationService.getLeavesOfAnEmployee(empId);
    }

    @Authenticated
    @PostMapping("/deleteMyLeave")
    public void deleteMyLeave(@RequestParam String leaveId) throws Exception {
        leaveApplicationService.deleteLeave(leaveId);
    }

    @Authenticated
    @PostMapping("/update")
    public LeaveApplicationUpdateDto update(@RequestBody LeaveApplicationUpdateDto leaveApplicationUpdateDto) throws Exception {
        return leaveApplicationService.updateLeaveApplication(leaveApplicationUpdateDto);
    }

    @Authenticated
    @PostMapping("/manageLeave")
    public LeaveApplication manageLeave(@RequestParam String leaveId, @RequestParam LeaveStatus status, @RequestParam String loggedInAccountId) throws Exception {
        return leaveApplicationService.approvalAndRejectionByManager(leaveId, status, loggedInAccountId);
    }

    @GetMapping("/getByManager")
    public List<LeaveApplication> getByManager(@RequestParam String managerId) throws Exception {
        return leaveApplicationService.getByManagerId(managerId);
    }
}
