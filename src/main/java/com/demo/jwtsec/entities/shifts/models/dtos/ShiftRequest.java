package com.demo.jwtsec.entities.shifts.models.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShiftRequest {

    private String status;
    private String petName;
    private LocalDate date;
    private LocalTime time;
    private LocalDateTime dateTime;
}
