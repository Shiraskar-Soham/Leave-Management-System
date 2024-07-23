package com.example.lams.Repository;

import com.example.lams.domain.LeaveApplication;
import com.example.lams.domain.LeaveApplicationIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface LeaveApplicationIndexRepository extends ElasticsearchRepository<LeaveApplicationIndex, String> {
    void save(LeaveApplicationIndex leaveApplicationIndex);

    List<LeaveApplicationIndex> getAllLeaves();

}
