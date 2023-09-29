package com.demo.jwtsec.entities.admins.repository;

import com.demo.jwtsec.entities.admins.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
