package com.example.lams.service;

import com.example.lams.Repository.LeaveApplicationRepository;
import com.example.lams.domain.Employee;
import com.example.lams.domain.LeaveApplication;
import com.example.lams.dtos.LeaveApplicationUpdateDto;
import com.example.lams.enums.LeaveStatus;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.chat.v1.HangoutsChat;
import com.google.api.services.chat.v1.model.Message;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class LeaveApplicationService {
    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;

    @Autowired
    private EmployeeService employeeService;

    private HangoutsChat chatService;

    @PostConstruct
    private void init()
            throws Exception {
        FileInputStream credentialsStream = new FileInputStream("/home/soham/Downloads/ecopool-550d3-b778c3da9ef4.json");
        GoogleCredential credentials = GoogleCredential.fromStream(credentialsStream)
                .createScoped(Collections.singleton("https://www.googleapis.com/auth/chat.messages"));
        // Build the Google Chat client
        chatService = new HangoutsChat.Builder(credentials.getTransport(), credentials.getJsonFactory(), credentials)
                .setApplicationName("Oasys Bot")
                .build();
    }

    public boolean createLeaveApplication(LeaveApplication leaveApplication) throws Exception {
        if(ObjectUtils.isEmpty(leaveApplication)){
            throw new Exception("Leave Application cannot be empty");
        }
        Employee e = employeeService.getEmployeeByEmpId(leaveApplication.getEmpId());
        leaveApplication.setManagerId(e.getManagerId());
        leaveApplication.setEmpName(e.getEmpName());
        leaveApplication.setDateCreated(System.currentTimeMillis());
        leaveApplication.setDateModified(System.currentTimeMillis());
        leaveApplication.setStatus(LeaveStatus.PENDING);
        leaveApplication.setIsDeleted(false);
        String text = "Leave Created by " + leaveApplication.getEmpId() + ". Kindly check the same.";
        Message message = new Message().setName("Leave Created").setText(text);
        chatService.spaces().messages().create("spaces/AAAANQoqCm0", message).execute();
        leaveApplicationRepository.save(leaveApplication);
        return true;
    }

    public List<LeaveApplication> getLeavesOfAnEmployee(String empId) throws Exception {
        if (ObjectUtils.isEmpty(empId)){
            throw new Exception("empId cannot be empty");
        }
        return leaveApplicationRepository.findByEmpId(empId);
    }

    public LeaveApplication getLeaveApplication(String leaveId) throws Exception {
        if(ObjectUtils.isEmpty(leaveId)){
            throw new Exception("leaveId cannot be empty");
        }
        return leaveApplicationRepository.findByLeaveId(leaveId);
    }

    public void deleteLeave(String leaveId) throws Exception {
        if(ObjectUtils.isEmpty(leaveId)){
            throw new Exception("leaveId cannot be empty");
        }
        LeaveApplication l = leaveApplicationRepository.findByLeaveId(leaveId);
        if(ObjectUtils.isEmpty(leaveId)){
            throw new Exception("No leave found for leaveId = " + leaveId);
        }
        l.setIsDeleted(true);
        l.setDateModified(System.currentTimeMillis());
        leaveApplicationRepository.save(l);
    }

    public LeaveApplicationUpdateDto updateLeaveApplication(LeaveApplicationUpdateDto leaveApplicationUpdateDto) throws Exception {
        if(ObjectUtils.isEmpty(leaveApplicationUpdateDto)){
            throw new Exception("leaveApplicationDto cannot be empty");
        }
        LeaveApplication l = leaveApplicationRepository.findByLeaveId(leaveApplicationUpdateDto.getLeaveId());
        if(ObjectUtils.isEmpty(leaveApplicationUpdateDto)){
            throw new Exception("No leave found for the leaveApplicationDto");
        }
        if(l.getStatus()!=LeaveStatus.PENDING){
            throw new Exception("Leave is already " + l.getStatus() + ".");
        }
        l.setDateModified(System.currentTimeMillis());
        l.setReason(leaveApplicationUpdateDto.getReason());
        l.setStartDate(leaveApplicationUpdateDto.getStartDate());
        l.setEndDate(leaveApplicationUpdateDto.getEndDate());
        leaveApplicationRepository.save(l);
        return leaveApplicationUpdateDto;
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
        if (leaveApplication.getStatus() != LeaveStatus.PENDING) {
            throw new Exception("Leave is already marked approved or rejected");
        }
        leaveApplication.setStatus(status);
        leaveApplication.setDateModified(System.currentTimeMillis());
        return leaveApplicationRepository.save(leaveApplication);
    }

    public List<LeaveApplication> getByManagerId(String managerId) throws Exception {
        if(ObjectUtils.isEmpty(managerId)){
            throw new Exception("Manager Id cannot be null.");
        }
        return leaveApplicationRepository.findByManagerId(managerId);
    }
}