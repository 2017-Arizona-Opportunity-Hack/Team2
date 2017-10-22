package com.team2.azcendapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.team2.azcendapi.model.BillType;
import com.team2.azcendapi.model.generic.HttpResponse;
import com.team2.azcendapi.services.BillTypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/billType", produces = MediaType.APPLICATION_JSON_VALUE)
public class BillTypeController {

    private final BillTypeService billTypeService;

    @Autowired
    public BillTypeController(BillTypeService billTypeService) {
        this.billTypeService = billTypeService;
    }

    @ApiOperation(nickname = "add gl ids to billType", value = "/gl/{}")
    @ApiResponses({
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 204, message = "OK")
    })
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType
            .APPLICATION_JSON_VALUE,value = "/gl/{billId}")
    public ResponseEntity<?> addGlIds(@PathVariable("billId") String
                                                  billId, @RequestBody JsonNode node) {
        if(billTypeService.addGlIds(billId,node)){
            HttpResponse httpResponse = new HttpResponse(
                    HttpStatus.NO_CONTENT.value(), "job ids added"
            );
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(httpResponse);
        }
        return null;
    }

    @ApiOperation(nickname = "add Bill type", value = "/")
    @ApiResponses({
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 204, message = "OK")
    })
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType
            .APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addBillType(@RequestBody BillType billType) {
        if(billTypeService.addBillType(billType)){
            HttpResponse httpResponse = new HttpResponse(
                    HttpStatus.NO_CONTENT.value(), "Bill Type Added"
            );
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(httpResponse);
        }
        return null;
    }
}
