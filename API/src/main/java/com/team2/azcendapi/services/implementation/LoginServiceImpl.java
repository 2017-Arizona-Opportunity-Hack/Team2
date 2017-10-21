package com.team2.azcendapi.services.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team2.azcendapi.model.User;
import com.team2.azcendapi.repository.UserRepository;
import com.team2.azcendapi.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class LoginServiceImpl implements LoginService {
    private UserRepository userRepository;
    private ObjectMapper objectMapper;

    @Autowired
    LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public boolean authenticateUser(String user_id, String password) {
        User user = this.userRepository.findByUser_idAndPassword(user_id, password);
        return user != null;
    }

    @Override
    public ResponseEntity<?> addUser(String input) {
        User user = null;
        User user_id = null;
        try {
            user = objectMapper.readValue(input, User.class);
            if (user.getUser_id() ==null && user.getPassword() ==null && user.getEmail() ==null && user.getRole() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email id and password required");
            }
            user_id = userRepository.findByUser_id(user.getUser_id());
            if (user_id != null) {
                return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("User id Exists");
            } else {
                this.userRepository.save(user);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unable to parse request");
        }
    }

}
