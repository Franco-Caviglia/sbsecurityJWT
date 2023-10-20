package com.demo.jwtsec.loginjwt.auth;

import com.demo.jwtsec.loginjwt.auth.Requests.LoginRequest;
import com.demo.jwtsec.loginjwt.auth.Requests.RegisterRequest;
import com.demo.jwtsec.loginjwt.auth.Response.AuthResponse;
import com.demo.jwtsec.loginjwt.auth.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    //Endpoint para iniciar sesion;
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }


    //Endpoint para registrarse como usuario;
    @PostMapping(value = "registerUser")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.registerUser(registerRequest));
    }

    //Endpoint para registrarse como administrador;
    @PostMapping(value = "registerAdmin")
    public ResponseEntity<AuthResponse> registerAdmin(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.registerAdmin(registerRequest));
    }
}
