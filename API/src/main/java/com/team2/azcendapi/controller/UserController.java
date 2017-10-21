package com.team2.azcendapi.controller;

import com.team2.azcendapi.services.LoginService;
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String authenticateUser(@RequestHeader("email") String email, @RequestHeader("password") String password) {
        Boolean loginStatus = this.loginService.authenticateUser(email, password);
        if (loginStatus) return "Success";
        return "Login Failed";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addUser(@RequestBody String input) {
        return this.loginService.addUser(input);
    }
}
