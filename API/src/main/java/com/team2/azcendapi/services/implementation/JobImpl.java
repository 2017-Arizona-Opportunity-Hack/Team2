package com.team2.azcendapi.services.implementation;

import com.team2.azcendapi.model.Job;
import com.team2.azcendapi.repository.JobRepository;
import com.team2.azcendapi.services.JobService;
import com.team2.azcendapi.services.MetadataImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.cellprocessor.ift.CellProcessor;

import java.util.List;

@ControllerAdvice
public class JobImpl implements JobService {

    private final JobRepository jobRepository;
    private final MetadataImportService importService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public JobImpl(JobRepository jobRepository, MetadataImportService importService) {
        this.jobRepository = jobRepository;
        this.importService = importService;
    }

    @Override
    public List<Job> importJobs(MultipartFile file) {
        String headers[] = Job.getFmpCsvImportFileHeaders();
        CellProcessor[] processors = Job.getFmpCsvImportFileCellProcessors();
        List<Job> jobs = importService.readEntitiesFromCsv(file, processors,
                headers, Job.class);
        saveJobs(jobs);
        return jobs;
    }

    @Transactional
    void saveJobs(List<Job> jobs) {
        for (Job job : jobs) {
            Job jobFromDb = jobRepository.findByJobId(job.getJobId());
            if (jobFromDb != null) {
                job.setId(jobFromDb.getId());
            }
            logger.info("saving :{jobId: {}, jobDescription: {}}", job
                    .getJobId(), job.getJobDescription());
            jobRepository.save(job);
        }

    }
}
