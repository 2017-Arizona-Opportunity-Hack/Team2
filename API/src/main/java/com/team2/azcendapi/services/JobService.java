package com.team2.azcendapi.services;

import com.team2.azcendapi.model.Job;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JobService {

    List<Job> importJobs(MultipartFile file);
}
