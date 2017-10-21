package com.team2.azcendapi.services;

import org.springframework.http.ResponseEntity;

public interface LoginService {

    boolean authenticateUser(String user_id, String password);

    ResponseEntity<?> addUser(String input);
}
