package com.team2.azcendapi.services.implementation;

import com.team2.azcendapi.model.GeneralLedger;
import com.team2.azcendapi.repository.GeneralLedgerRepository;
import com.team2.azcendapi.services.GeneralLedgerService;
import com.team2.azcendapi.services.MetadataImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.cellprocessor.ift.CellProcessor;

import java.util.List;

@Component
public class GeneralLedgerServiceImpl implements GeneralLedgerService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final GeneralLedgerRepository generalLedgerRepository;

    private final MetadataImportService importService;

    @Autowired
    public GeneralLedgerServiceImpl(GeneralLedgerRepository generalLedgerRepository, MetadataImportService importService) {
        this.generalLedgerRepository = generalLedgerRepository;
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
