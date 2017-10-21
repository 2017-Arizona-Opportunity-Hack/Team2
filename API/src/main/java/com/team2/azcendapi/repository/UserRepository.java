package com.team2.azcendapi.repository;

import com.team2.azcendapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUser_idAndPassword(String user_id, String password);
    User findByUser_id(String user_id);
}
