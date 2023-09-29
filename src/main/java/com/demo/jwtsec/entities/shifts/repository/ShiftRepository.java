package com.demo.jwtsec.entities.shifts.repository;

import com.demo.jwtsec.entities.shifts.models.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
}
