package com.team2.azcendapi.services.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.team2.azcendapi.exception.BadRequestException;
import com.team2.azcendapi.model.GeneralLedger;
import com.team2.azcendapi.model.Invoice;
import com.team2.azcendapi.repository.GeneralLedgerRepository;
import com.team2.azcendapi.repository.InvoiceRepository;
import com.team2.azcendapi.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceServiceImpl implements InvoiceService{

    private final InvoiceRepository invoiceRepository;

    private final GeneralLedgerRepository generalLedgerRepository;
    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, GeneralLedgerRepository generalLedgerRepository) {
        this.invoiceRepository = invoiceRepository;
        this.generalLedgerRepository = generalLedgerRepository;
    }

    @Override
    public boolean addInvoice(JsonNode rawData) {
        if(!rawData.has("gl_id") && !rawData.has("invoice_amount") &&
                !rawData.has("month")){
            throw new BadRequestException("Parameter error");
        }
        GeneralLedger generalLedger = generalLedgerRepository.findByGlId
                (rawData.get("gl_id").asInt());
        if(generalLedger == null){
            throw  new BadRequestException("GL id is invalid");
        }
        Invoice invoice;
//        invoice = invoiceRepository.
        return false;
    }
}
