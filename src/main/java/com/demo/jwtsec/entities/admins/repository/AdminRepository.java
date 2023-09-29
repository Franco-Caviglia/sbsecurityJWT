package com.demo.jwtsec.admins.repository;

import com.demo.jwtsec.admins.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
