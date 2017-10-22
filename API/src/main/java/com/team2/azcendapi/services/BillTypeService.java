package com.team2.azcendapi.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.team2.azcendapi.model.BillType;

public interface BillTypeService {
    boolean addBillType(BillType billType);

    boolean addGlIds(String billId, JsonNode node);
}
