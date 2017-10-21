package com.team2.azcendapi.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.team2.azcendapi.model.Budget;
import org.springframework.web.multipart.MultipartFile;

public interface BudgetService {

    boolean addBudget(JsonNode rawData);

    boolean addBuget(MultipartFile file);

    Budget getBudget(String jobId);
}
