package com.team2.azcendapi.model;

import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int jobId;
    private String jobDescription;

    public Job() {
        super();
    }

    public Job(int jobId, String jobDescription) {
        this.jobId = jobId;
        this.jobDescription = jobDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public static String[] getFmpCsvImportFileHeaders() {
        return new String[]{"jobId", "jobDescription"};
    }

    public static CellProcessor[] getFmpCsvImportFileCellProcessors() {
        return new CellProcessor[]{
                new org.supercsv.cellprocessor.constraint.NotNull(new ParseInt()),
                new org.supercsv.cellprocessor.constraint.NotNull()
        };
    }

}

