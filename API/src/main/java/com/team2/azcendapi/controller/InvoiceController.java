package com.team2.azcendapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/invoice", produces = MediaType.APPLICATION_JSON_VALUE)
public class InvoiceController {

    @ApiOperation(nickname = "Add invoice to database", value = "/invoice")
    @ApiResponses({
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Incorrect data"),
            @ApiResponse(code = 204, message = "OK")
    })
    @RequestMapping(method = RequestMethod.PUT, consumes = "multipart/form-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String addInvoice(@RequestBody JsonNode node) {
        return "{\"status\":204}";
    }
}
