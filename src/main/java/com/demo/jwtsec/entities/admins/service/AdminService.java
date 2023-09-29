package com.demo.jwtsec.entities.admins.service;

import com.demo.jwtsec.entities.admins.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
}
