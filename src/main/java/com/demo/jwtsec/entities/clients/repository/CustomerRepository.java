package com.demo.jwtsec.entities.clients.repository;

import com.demo.jwtsec.entities.clients.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends
        JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
}
