package com.team2.azcendapi.controller;

import com.team2.azcendapi.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
public class JobsController {

    private final JobService jobService;

    @Autowired
    public JobsController(JobService jobService) {
        this.jobService = jobService;
    }


    @RequestMapping(method = RequestMethod.PUT, consumes = "multipart/form-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String importBooks(@RequestParam("file") MultipartFile file) {
        jobService.importJobs(file);
        return "{\"status\":204}";
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getJobs() {
        return "Hi";
    }
}
