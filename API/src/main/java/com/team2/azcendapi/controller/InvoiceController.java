package com.team2.azcendapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/invoice", produces = MediaType.APPLICATION_JSON_VALUE)
public class InvoiceController {

    @RequestMapping(method = RequestMethod.PUT, consumes = "multipart/form-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String addInvoice(@RequestBody JsonNode node) {
        jobService.importJobs(file);
        return "{\"status\":204}";
    }
}
