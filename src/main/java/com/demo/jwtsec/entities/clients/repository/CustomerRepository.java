package com.demo.jwtsec.clients.repository;

import com.demo.jwtsec.clients.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends
        JpaRepository<Customer, Long> {
}
