package com.team2.azcendapi.controller;

import com.team2.azcendapi.model.User;
import com.team2.azcendapi.model.generic.HttpResponse;
import com.team2.azcendapi.services.LoginService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final LoginService loginService;

    @Autowired
    public UserController(LoginService loginService) {
        this.loginService = loginService;
    }

    @ApiOperation(nickname = "Check user credentials for Login", value = "/user/login")
    @ApiResponses({
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Incorrect data"),
            @ApiResponse(code = 204, message = "OK")
    })
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> authenticateUser(@RequestHeader("user_id") String
                                                      userId,
                                              @RequestHeader("password") String password) {
        Boolean loginStatus = loginService.authenticateUser(userId, password);
        HttpResponse httpResponse = new HttpResponse(
                HttpStatus.ACCEPTED.value(), "user authenticated"
        );
        if (loginStatus) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(httpResponse);
        }
        return null;
    }

    @ApiOperation(nickname = "Add user to system", value = "/user/add")
    @ApiResponses({
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Incorrect data"),
            @ApiResponse(code = 204, message = "OK")
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addUser(@RequestBody User user) {
        return this.loginService.addUser(user);
    }
}
