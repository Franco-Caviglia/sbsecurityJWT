package com.demo.jwtsec.entities.pets.controller;


import com.demo.jwtsec.entities.pets.models.Pets;
import com.demo.jwtsec.entities.pets.models.dtos.PetRequest;
import com.demo.jwtsec.entities.pets.service.PetService;
import com.demo.jwtsec.loginjwt.auth.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test/pets")
@RequiredArgsConstructor
public class TestPetController {

    private final PetService petService;

    @PostMapping("/{id}/addPet")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pets> addPetToList(@RequestBody PetRequest petRequest){
        return ResponseEntity.ok(petService.addPetProfile( petRequest));
    }

    @GetMapping("/readPets")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Pets>> getPets(){
        return ResponseEntity.ok(petService.getAllPets());
    }


}
