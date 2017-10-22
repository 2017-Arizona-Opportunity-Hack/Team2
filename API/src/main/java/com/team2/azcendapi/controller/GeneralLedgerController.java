package com.team2.azcendapi.controller;

import com.team2.azcendapi.model.GeneralLedger;
import com.team2.azcendapi.services.GeneralLedgerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
}
