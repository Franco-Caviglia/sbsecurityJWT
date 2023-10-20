package com.demo.jwtsec.entities.clients.controller;


import com.demo.jwtsec.entities.clients.models.Pets;
import com.demo.jwtsec.entities.clients.models.dtos.PetRequest;
import com.demo.jwtsec.entities.clients.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test/pets")
@RequiredArgsConstructor
public class TestPetController {
    /*
    private final CustomerService customerService;

    @PostMapping("/addPet")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pets> addPetToList(@RequestBody PetRequest petRequest){
        return ResponseEntity.ok(customerService.addPetProfile(petRequest));
    }

    @GetMapping("/readPets")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Pets>> getPets(){
        return ResponseEntity.ok(customerService.getAllPets());
    }

     */
}
