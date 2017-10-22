package com.team2.azcendapi.services.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.team2.azcendapi.exception.BadRequestException;
import com.team2.azcendapi.model.GeneralLedger;
import com.team2.azcendapi.model.Job;
import com.team2.azcendapi.repository.GeneralLedgerRepository;
import com.team2.azcendapi.repository.JobRepository;
import com.team2.azcendapi.services.GeneralLedgerService;
import com.team2.azcendapi.services.MetadataImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.cellprocessor.ift.CellProcessor;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Component
public class GeneralLedgerServiceImpl implements GeneralLedgerService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final GeneralLedgerRepository generalLedgerRepository;

    private final JobRepository jobRepository;

    private final MetadataImportService importService;

    @Autowired
    public GeneralLedgerServiceImpl(GeneralLedgerRepository generalLedgerRepository, JobRepository jobRepository, MetadataImportService importService) {
        this.generalLedgerRepository = generalLedgerRepository;
        this.jobRepository = jobRepository;
        this.importService = importService;
    }

    @Override
    public List<GeneralLedger> importGL(MultipartFile file) {
        String headers[] = GeneralLedger.getFmpCsvImportFileHeaders();
        CellProcessor[] processors = GeneralLedger.getFmpCsvImportFileCellProcessors();
        List<GeneralLedger> generalLedgers = importService
                .readEntitiesFromCsv(file, processors, headers, GeneralLedger.class);
        saveGeneralLedgers(generalLedgers);
        return generalLedgers;
    }

    @Override
    public List<GeneralLedger> getAllGLs() {
        return generalLedgerRepository.findAll();
    }

    @Override
    public boolean addJobIds(int glId, JsonNode node) {
        GeneralLedger generalLedger = generalLedgerRepository.findByGlId(glId);
        if(generalLedger==null){
            throw new BadRequestException("This GL id is invalid");
        }
        if(!node.has("job_ids")){
            throw new BadRequestException("job_ids is not present");
        }
        Set<Job> jobIds = new LinkedHashSet<>();
        for(JsonNode singleId : node.get("job_ids")){
            Job job = jobRepository.findByJobId(singleId.asInt());
            if(job ==null){
                throw new BadRequestException("This "+singleId.asInt() +" Job id does not " +
                        "exist");
            }else{
                jobIds.add(job);
            }
        }
        generalLedger.setJobs(jobIds);
        generalLedgerRepository.save(generalLedger);
        return true;
    }

    @Transactional
    void saveGeneralLedgers(List<GeneralLedger> generalLedgers) {
        for (GeneralLedger generalLedger : generalLedgers) {
            GeneralLedger generalLedgerFromDb = generalLedgerRepository
                    .findByGlId(generalLedger.getGlId());
            if(generalLedgerFromDb != null){
                generalLedger.setId(generalLedgerFromDb.getId());
            }
            logger.info("saving {id:{}, desc: {}}",generalLedger.getGlId(),
                    generalLedger.getGlDescription());
            generalLedgerRepository.save(generalLedger);
        }

    }
}
