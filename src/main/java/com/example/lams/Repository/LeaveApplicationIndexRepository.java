package com.example.lams.Repository;

import com.example.lams.domain.LeaveApplicationIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface LeaveApplicationIndexRepository extends ElasticsearchRepository<LeaveApplicationIndex, String> {

    List<LeaveApplicationIndex> getAllLeaves();

}
