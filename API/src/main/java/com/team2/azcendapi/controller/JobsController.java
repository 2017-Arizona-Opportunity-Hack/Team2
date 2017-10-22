package com.team2.azcendapi.controller;

import com.team2.azcendapi.model.Job;
import com.team2.azcendapi.services.JobService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
public class JobsController {

    private final JobService jobService;

    @Autowired
    public JobsController(JobService jobService) {
        this.jobService = jobService;
    }

    @ApiOperation(nickname = "Import Jobs from file", value = "/jobs")
    @ApiResponses({
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Incorrect data"),
            @ApiResponse(code = 204, message = "OK")
    })
    @RequestMapping(method = RequestMethod.PUT, consumes = "multipart/form-data", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String importBooks(@RequestParam("file") MultipartFile file) {
        jobService.importJobs(file);
        return "{\"status\":204}";
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Job> getJobs() {
        return jobService.getAllJobs();
    }


}
