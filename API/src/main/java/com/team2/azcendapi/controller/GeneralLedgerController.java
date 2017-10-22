package com.team2.azcendapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.team2.azcendapi.model.GeneralLedger;
import com.team2.azcendapi.model.generic.HttpResponse;
import com.team2.azcendapi.services.GeneralLedgerService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/gl", produces = MediaType
        .APPLICATION_JSON_VALUE)
public class GeneralLedgerController {

    private final GeneralLedgerService generalLedgerService;

    @Autowired
    public GeneralLedgerController(GeneralLedgerService generalLedgerService) {
        this.generalLedgerService = generalLedgerService;
    }

    @ApiOperation(nickname = "Import General Ledger from file", value = "/gl")
    @ApiResponses({
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Incorrect data"),
            @ApiResponse(code = 204, message = "OK")
    })
    @RequestMapping(method = RequestMethod.PUT, consumes = "multipart/form-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String importGl(@RequestParam("file") MultipartFile file) {
        generalLedgerService.importGL(file);
        return "{\"status\":204}";
    }

    @ApiOperation(nickname = "Get all GLs", value = "/gl")
    @ApiResponses({
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 204, message = "OK")
    })
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GeneralLedger> getAllGls() {
        return generalLedgerService.getAllGLs();
    }

    @ApiOperation(nickname = "add job ids to gl", value = "/gl/jobid")
    @ApiResponses({
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 204, message = "OK")
    })
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType
            .APPLICATION_JSON_VALUE,value = "/job/{glId}")
    public ResponseEntity<?> addJobIds(@PathVariable("glId") int
                                                   glId, @RequestBody JsonNode node) {
        if(generalLedgerService.addJobIds(glId,node)){
            HttpResponse httpResponse = new HttpResponse(
                    HttpStatus.NO_CONTENT.value(), "job ids added"
            );
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(httpResponse);
        }
        return null;
    }
}
