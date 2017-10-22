package com.team2.azcendapi.controller;

import com.team2.azcendapi.model.Budget;
import com.team2.azcendapi.model.generic.HttpResponse;
import com.team2.azcendapi.services.BudgetService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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


    @ApiOperation(nickname = "Add budget in Database", value = "/budget")
    @ApiResponses({
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Incorrect data"),
            @ApiResponse(code = 202, message = "Created")
    })
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType
            .APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> addBudget(@RequestBody Budget budget) {
        if (budgetService.addBudget(budget)) {
            HttpResponse response = new HttpResponse(HttpStatus.CREATED
                    .value(), "Budget Created");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return null;
    }
}
