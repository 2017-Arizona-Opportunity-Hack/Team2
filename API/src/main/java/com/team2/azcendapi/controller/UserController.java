package com.team2.azcendapi.controller;

import com.team2.azcendapi.model.User;
import com.team2.azcendapi.services.LoginService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public String authenticateUser(@RequestHeader("email") String email, @RequestHeader("password") String password) {
        Boolean loginStatus = this.loginService.authenticateUser(email, password);
        if (loginStatus) return "Success";
        return "Login Failed";
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
