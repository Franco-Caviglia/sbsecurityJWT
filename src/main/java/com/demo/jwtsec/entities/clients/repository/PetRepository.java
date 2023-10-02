package com.demo.jwtsec.entities.clients.repository;

import com.demo.jwtsec.entities.clients.models.Pets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pets, Long> {
}
