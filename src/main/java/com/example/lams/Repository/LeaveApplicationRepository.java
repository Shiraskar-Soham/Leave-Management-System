package com.example.lams.Repository;


import com.example.lams.domain.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, String>  {

    LeaveApplication findByLeaveId(String leaveId);

    List<LeaveApplication> findByEmpId(String empId);
}
