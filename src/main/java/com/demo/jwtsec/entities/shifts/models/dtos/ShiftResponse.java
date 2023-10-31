package com.demo.jwtsec.entities.shifts.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShiftResponse {

    private String petName;
    private String email;
    private String disease;
    private LocalDate date;
    private LocalTime time;
    private String status;

}
