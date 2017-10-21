package com.team2.azcendapi.repository;

import com.team2.azcendapi.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUserIdAndPassword(String userId, String password);

    User findByUserId(String userId);
}
