package com.demo.jwtsec.entities.admins.repository;

import com.demo.jwtsec.entities.admins.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}
