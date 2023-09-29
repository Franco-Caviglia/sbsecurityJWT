package com.demo.jwtsec.entities.clients.service;

import com.demo.jwtsec.entities.clients.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

}
