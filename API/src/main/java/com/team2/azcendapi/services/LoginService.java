package com.team2.azcendapi.services;

import com.team2.azcendapi.model.User;
import org.springframework.http.ResponseEntity;

public interface LoginService {

    boolean authenticateUser(String user_id, String password);

    ResponseEntity<?> addUser(User input);
}
