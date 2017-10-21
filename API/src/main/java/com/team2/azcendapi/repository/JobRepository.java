package com.team2.azcendapi.repository;

import com.team2.azcendapi.model.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<Job,Integer> {

    Job findByJobId(int jobId);
}
