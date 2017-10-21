package com.team2.azcendapi.services.implementation;

import com.team2.azcendapi.model.User;
import com.team2.azcendapi.model.generic.HttpResponse;
import com.team2.azcendapi.repository.UserRepository;
import com.team2.azcendapi.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoginServiceImpl implements LoginService {
    private UserRepository userRepository;

    @Autowired
    LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean authenticateUser(String user_id, String password) {
        User user = this.userRepository.findByUserIdAndPassword(user_id, password);
        return user != null;
    }

    @Override
    public ResponseEntity<?> addUser(User user) {
        User user_id;
        try {
            if (user.getUserId() == null && user.getPassword() == null && user.getEmail
                    () == null && user.getRole() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email id and password required");
            }
            user_id = userRepository.findByUserId(user.getUserId());
            if (user_id != null) {
                return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("User id Exists");
            } else {
                HttpResponse response = new HttpResponse(HttpStatus.ACCEPTED
                        .value(),"success");
                this.userRepository.save(user);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unable to parse request");
        }
    }

}
