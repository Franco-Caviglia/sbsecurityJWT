package com.demo.jwtsec.loginjwt.auth;

import com.demo.jwtsec.loginjwt.auth.Requests.LoginRequest;
import com.demo.jwtsec.loginjwt.auth.Requests.RegisterRequest;
import com.demo.jwtsec.loginjwt.auth.Response.AuthResponse;
import com.demo.jwtsec.loginjwt.auth.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "loginAdmin")
    public ResponseEntity<AuthResponse> loginAdmin(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.loginAdmin(loginRequest));
    }

    @PostMapping(value = "registerAdmin")
    public ResponseEntity<AuthResponse> registerAdmin(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.registerAdmin(registerRequest));
    }

    @PostMapping(value = "loginCustomer")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.loginUser(loginRequest));
    }

    @PostMapping(value = "registerCustomer")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.registerUser(registerRequest));
    }
}
