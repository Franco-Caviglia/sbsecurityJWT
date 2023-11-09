package com.demo.jwtsec.entities.admins.controller;


import com.demo.jwtsec.entities.pets.models.Pets;
import com.demo.jwtsec.entities.pets.models.dtos.PetRequest;
import com.demo.jwtsec.entities.pets.models.dtos.PetResponse;
import com.demo.jwtsec.entities.pets.service.PetService;
import com.demo.jwtsec.entities.shifts.models.Shift;
import com.demo.jwtsec.entities.shifts.models.dtos.ShiftRequest;
import com.demo.jwtsec.entities.shifts.models.dtos.ShiftResponse;
import com.demo.jwtsec.entities.shifts.service.ShiftService;
import com.demo.jwtsec.loginjwt.auth.Service.UserService;
import com.demo.jwtsec.loginjwt.auth.User.Response.UserResponse;
import com.demo.jwtsec.loginjwt.auth.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
    //Testing;
    @GetMapping("/test")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String welcome(){
        return "HOla";
    }

    private final PetService petService;
    private final ShiftService shiftService;
    private final UserService userService;
    //TODO acciones permitidas para admins -> readUserProfiles();


    //-------------------- Shifts -----------------------------------------------------


    @PostMapping("/{pet_id}/addShiftToPet")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ShiftResponse> addShiftToPet(@PathVariable Long pet_id, @RequestBody ShiftRequest shiftRequest){
        return ResponseEntity.ok(shiftService.registerShiftToPet(pet_id, shiftRequest));
    }

    @GetMapping("/readAllShifts")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<ShiftResponse>> getAllShifts(){
        return ResponseEntity.ok(shiftService.getAllShifts());
    }

    @PutMapping("/{id}/markCompleteShifts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Shift> markCompleteShifts(@PathVariable Long id, @RequestBody ShiftResponse shiftResponse){
        return ResponseEntity.ok(shiftService.markCompleteShifts(id, shiftResponse));
    }

    //Borrar y crear shift nuevo;
    /*
    @PutMapping("/{id}/editShifts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Shift> editShifts(@PathVariable Long id, @RequestBody ShiftResponse shiftResponse){
        return ResponseEntity.ok(shiftService.editShifts(id, shiftResponse));
    } */

    @DeleteMapping("/{id}/deleteShifts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Boolean>> deleteShift(@PathVariable Long id){
        return ResponseEntity.ok(shiftService.deleteShift(id));
    }

    //-------------------- Shifts -----------------------------------------------------


    //-------------------- Pets -----------------------------------------------------
    //Agrega la mascota al usuario seleccionado el cual se identifica por id;
    @PostMapping("/{id}/addPet")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PetResponse> addPetToList(@PathVariable Long id, @RequestBody PetRequest petRequest){
        return ResponseEntity.ok(petService.addPetProfileFromAdmin(id, petRequest));
    }

    @GetMapping("/{userid}/readPets")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PetResponse>> getPets(@PathVariable Long userid){
        return ResponseEntity.ok(petService.getUserPets(userid));
    }

    @PutMapping("/{id}/editPet")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PetResponse> editPet(@PathVariable Long id, @RequestBody PetResponse petResponse){
        return ResponseEntity.ok(petService.editPet(id, petResponse));
    }


    //-------------------- Customers -----------------------------------------------------

    //@GetMapping("/{id_customer}/readProfile")
    //@ResponseStatus(HttpStatus.OK)
    //public ResponseEntity<User> getCustomerProfile(@PathVariable Integer id, @RequestBody CustomerResponse customerResponse){
   //     return ResponseEntity.ok(customerService.getCustomerProfileById(id, customerResponse));
    //}
    @GetMapping("/readAllUsers")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


}
