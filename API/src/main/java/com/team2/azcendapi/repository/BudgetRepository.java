package com.team2.azcendapi.repository;

import com.team2.azcendapi.model.Budget;
import com.team2.azcendapi.model.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends CrudRepository<Budget,Integer>{

    Budget findByJob(Job job);
}
