package com.demo.jwtsec.entities.admins.controller;

import com.demo.jwtsec.entities.clients.controller.CustomerController;
import com.demo.jwtsec.entities.clients.models.Customer;
import com.demo.jwtsec.entities.clients.models.dtos.CustomerResponse;
import com.demo.jwtsec.entities.clients.service.CustomerService;
import com.demo.jwtsec.entities.shifts.models.Shift;
import com.demo.jwtsec.entities.shifts.models.dtos.ShiftRequest;
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

    private final CustomerService customerService;
    private final ShiftService shiftService;
    //TODO acciones permitidas para admins -> readUserProfiles();

    //Testing;
    @GetMapping("/test")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String welcome(){
        return "HOla";
    }
    //-------------------- Shifts -----------------------------------------------------

    @PostMapping("/addShift")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Shift> registerShift(@RequestBody ShiftRequest shiftRequest){
        return ResponseEntity.ok(shiftService.registerShift(shiftRequest).getBody());
    }

    @GetMapping("/readAllShifts")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<Shift>> getAllShifts(){
        return ResponseEntity.ok(shiftService.getAllShifts().getBody());
    }

    @PutMapping("/{id_shift}/markCompleteShifts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Shift> markCompleteShifts(@PathVariable Long id, @RequestBody ShiftResponse shiftResponse){
        return ResponseEntity.ok(shiftService.markCompleteShifts(id, shiftResponse));
    }

    @PutMapping("/{id_shift}/editShifts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Shift> editShifts(@PathVariable Long id, @RequestBody ShiftResponse shiftResponse){
        return ResponseEntity.ok(shiftService.editShifts(id, shiftResponse));
    }

    @DeleteMapping("/{id_shift}/deleteShifts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Boolean>> deleteShift(@PathVariable Long id){
        return ResponseEntity.ok(shiftService.deleteShift(id));
    }

    //-------------------- Shifts -----------------------------------------------------

    //-------------------- Customers -----------------------------------------------------
    @GetMapping("/{id_customer}/readProfile")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> getCustomerProfile(@PathVariable Long id, @RequestBody CustomerResponse customerResponse){
        return ResponseEntity.ok(customerService.getCustomerProfileById(id, customerResponse));
    }
}
