package com.team2.azcendapi.services;

import com.fasterxml.jackson.databind.JsonNode;

public interface InvoiceService {

    boolean addInvoice(JsonNode rawData);
}
