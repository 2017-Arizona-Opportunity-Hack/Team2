package com.team2.azcendapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.team2.azcendapi.model.generic.HttpResponse;
import com.team2.azcendapi.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/budget", produces = MediaType
        .APPLICATION_JSON_VALUE)
public class BudgetController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType
            .APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> importBooks(@RequestBody JsonNode node) {
        if (budgetService.addBudget(node)) {
            HttpResponse response =  new HttpResponse(HttpStatus.CREATED
                    .value(),"Budget Created");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return null;
    }
}
