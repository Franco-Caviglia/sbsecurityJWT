package com.demo.jwtsec.entities.pets.controller;


import com.demo.jwtsec.entities.pets.models.Pets;
import com.demo.jwtsec.entities.pets.models.dtos.PetRequest;
import com.demo.jwtsec.entities.pets.models.dtos.PetResponse;
import com.demo.jwtsec.entities.pets.service.PetService;
import com.demo.jwtsec.loginjwt.auth.User.Response.UserResponse;
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


    //metodo para el admin;
    @GetMapping("/{user_id}/readPets")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PetResponse>> getPets(@PathVariable Long user_id){
        return ResponseEntity.ok(petService.getUserPets(user_id));
    }


}
