package com.demo.jwtsec.Repository;

import com.demo.jwtsec.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    //QUERY methods;
    Optional<User> findByUsername(String username);
}
