package com.demo.jwtsec.entities.clients.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class CustomerController {

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String welcome(){
        return "HOla";
    }
    //TODO acciones permitidas para usuarios -> addShift(); deleteShift(); readShift(); readProfile(); registerPets(); editShift();



}
