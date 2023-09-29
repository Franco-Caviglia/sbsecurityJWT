package com.demo.jwtsec.entities.shifts.controller;

import com.demo.jwtsec.entities.shifts.models.Shift;
import com.demo.jwtsec.entities.shifts.models.dtos.ShiftRequest;
import com.demo.jwtsec.entities.shifts.repository.ShiftRepository;
import com.demo.jwtsec.entities.shifts.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test/shift")
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftService shiftService;
    private final ShiftRepository shiftRepository;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Shift> registerShift(@RequestBody ShiftRequest shiftRequest){
        Shift shift = shiftService.registerShift(shiftRequest).getBody();
        return ResponseEntity.ok(shift);
    }

    @GetMapping("/read")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<Shift>> getShifts(){

        return ResponseEntity.ok(shiftRepository.findAll());
    }
}
