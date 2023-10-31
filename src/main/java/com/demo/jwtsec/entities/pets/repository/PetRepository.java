package com.demo.jwtsec.entities.pets.repository;

import com.demo.jwtsec.entities.pets.models.Pets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pets, Long> {
}
