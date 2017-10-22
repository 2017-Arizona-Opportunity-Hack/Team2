package com.team2.azcendapi.services.implementation;

import com.team2.azcendapi.exception.BadRequestException;
import com.team2.azcendapi.model.Budget;
import com.team2.azcendapi.model.Job;
import com.team2.azcendapi.repository.BudgetRepository;
import com.team2.azcendapi.repository.JobRepository;
import com.team2.azcendapi.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class BudgetServiceImpl implements BudgetService{

    private final BudgetRepository budgetRepository;

    private final JobRepository jobRepository;
    @Autowired
    public BudgetServiceImpl(BudgetRepository budgetRepository, JobRepository jobRepository) {
        this.budgetRepository = budgetRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public boolean addBudget(Budget node) {
        if(node.getJobId() == 0 || node.getAmount() == 0){
            throw new BadRequestException("The supplied parameters are not " +
                    "correct");
        }
        Job job = jobRepository.findByJobId(node.getJobId());
        if(job == null){
            throw new BadRequestException("Job id does not exist");
        }
        Budget budget;
        budget = budgetRepository.findByJob(job);
        if(budget == null){
            budget = new Budget();
        }
        budget.setJob(job);
        budget.setAmount(node.getAmount());
        budgetRepository.save(budget);
        return true;
    }

    @Override
    public boolean addBuget(MultipartFile file) {
        return false;
    }

    @Override
    public Budget getBudget(String jobId) {
        return null;
    }
}
