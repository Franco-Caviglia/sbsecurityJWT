package com.demo.jwtsec.entities.shifts.repository;

import com.demo.jwtsec.entities.shifts.models.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ShiftRepository extends JpaRepository<Shift, Long> {

    Shift findByDateTime (LocalDateTime dateTime);
}
