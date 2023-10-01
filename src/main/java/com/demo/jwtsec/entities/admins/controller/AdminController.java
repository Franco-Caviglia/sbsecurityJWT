package com.demo.jwtsec.entities.admins.controller;

import com.demo.jwtsec.entities.shifts.models.Shift;
import com.demo.jwtsec.entities.shifts.models.dtos.ShiftResponse;
import com.demo.jwtsec.entities.shifts.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ShiftService shiftService;
    //TODO acciones permitidas para admins -> markCompleteShifts(); readUserProfiles(); editShifts();

    @GetMapping("/readAllShifts")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<Shift>> getAllShifts(){
        return ResponseEntity.ok(shiftService.getAllShifts().getBody());
    }

    @PutMapping("/markCompleteShifts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Shift> markCompleteShifts(@PathVariable Long id, @RequestBody ShiftResponse shiftResponse){
        return ResponseEntity.ok(shiftService.markCompleteShifts(id, shiftResponse));
    }

    @DeleteMapping("/{id}/deleteShifts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Boolean>> deleteShift(@PathVariable Long id){
        return ResponseEntity.ok(shiftService.deleteShift(id));
    }

}
