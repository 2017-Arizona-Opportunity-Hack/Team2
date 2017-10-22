package com.team2.azcendapi.services.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.team2.azcendapi.exception.BadRequestException;
import com.team2.azcendapi.model.BillType;
import com.team2.azcendapi.model.GeneralLedger;
import com.team2.azcendapi.repository.BillTypeRepository;
import com.team2.azcendapi.repository.GeneralLedgerRepository;
import com.team2.azcendapi.services.BillTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class BillTypeServiceImpl implements BillTypeService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final BillTypeRepository billTypeRepository;

    private final GeneralLedgerRepository generalLedgerRepository;

    @Autowired
    public BillTypeServiceImpl(BillTypeRepository billTypeRepository, GeneralLedgerRepository generalLedgerRepository) {
        this.billTypeRepository = billTypeRepository;
        this.generalLedgerRepository = generalLedgerRepository;
    }


    @Override
    public boolean addBillType(BillType billType) {
        BillType billTypeToSave = billTypeRepository.findByBillTypeId
                (billType.getBillTypeId());
        if (billTypeToSave == null) {
            billTypeToSave = new BillType();
        }
        billTypeToSave.setBillDescription(billType.getBillDescription());
        billTypeToSave.setBillTypeId(billType.getBillTypeId());
        billTypeRepository.save(billTypeToSave);
        return true;
    }

    @Override
    public boolean addGlIds(String billId, JsonNode node) {
        BillType billType = billTypeRepository.findByBillTypeId(billId);
        if(billType==null){
            throw new BadRequestException("This Bill id is invalid");
        }
        if(!node.has("gl_ids")){
            throw new BadRequestException("gl_ids is not present");
        }
        Set<GeneralLedger> generalLedgers = new LinkedHashSet<>();
        for(JsonNode singleId : node.get("gl_ids")){
            GeneralLedger generalLedger = generalLedgerRepository.findByGlId(singleId
                    .asInt());
            if(generalLedger ==null){
                throw new BadRequestException("This "+singleId.asInt() +" Gl " +
                        "id does not " +
                        "exist");
            }else{
                generalLedgers.add(generalLedger);
                logger.info("saving {billTypebid: {}, generalId: {}}",
                        billType.getBillTypeId(),generalLedger.getGlId());
            }
        }
        billType.setGeneralLedgerSet(generalLedgers);
        billTypeRepository.save(billType);
        return true;
    }
}
