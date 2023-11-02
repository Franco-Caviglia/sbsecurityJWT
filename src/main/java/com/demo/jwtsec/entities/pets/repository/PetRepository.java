package com.demo.jwtsec.entities.pets.repository;

import com.demo.jwtsec.entities.pets.models.Pets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pets, Long> {

    List<Pets> findByUserId(Long userid);
}
