package com.demo.jwtsec.loginjwt.auth.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Aqui estan los endpoints protegidos;
//TODO configrurar para usuario y crear controlador para admin;
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {

    @PostMapping("/demo")
    public String welcome(){
        return "Welcome from secure endpoint";
    }
}
