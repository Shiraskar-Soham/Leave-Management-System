package com.example.lams.Repository;


import com.example.lams.domain.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, String>  {

    LeaveApplication findByLeaveId(String leaveId);

    @Query(value = "SELECT * from leave_request WHERE emp_id = ?1 and is_deleted=false", nativeQuery = true)
    List<LeaveApplication> findByEmpId(String empId);

    @Query(value = "SELECT * from leave_request WHERE manager_id = ?1 and is_deleted=false", nativeQuery = true)
    List<LeaveApplication> findByManagerId(String managerId);
}
