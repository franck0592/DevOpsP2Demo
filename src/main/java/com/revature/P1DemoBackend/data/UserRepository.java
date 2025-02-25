package com.revature.P1DemoBackend.data;

import com.revature.P1DemoBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //define custom JPQL query to fin user by username
    User findByUsername(String username);

    //define JPQL query to find user by username and password
    User findByUsernameAndPassword(String username, String password);
}
