package com.demo.jwtsec.entities.clients.repository;

import com.demo.jwtsec.entities.clients.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends
        JpaRepository<Customer, Long> {
}
