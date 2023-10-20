package com.demo.jwtsec.loginjwt.auth.Service;

import com.demo.jwtsec.loginjwt.auth.Jwt.Service.JWTService;
import com.demo.jwtsec.loginjwt.auth.Repository.UserRepository;
import com.demo.jwtsec.loginjwt.auth.User.Role;
import com.demo.jwtsec.loginjwt.auth.Requests.LoginRequest;
import com.demo.jwtsec.loginjwt.auth.Requests.RegisterRequest;
import com.demo.jwtsec.loginjwt.auth.Response.AuthResponse;
import com.demo.jwtsec.loginjwt.auth.User.User;
import com.demo.jwtsec.mailsender.model.Email;
import com.demo.jwtsec.mailsender.service.EmailService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    public AuthResponse login(LoginRequest loginRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        UserDetails user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        String authorities = user.getAuthorities().toString();
        String username = user.getUsername();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .username(username)
                .authorities(authorities)
                .build();
    }

    public AuthResponse registerUser(RegisterRequest registerRequest){
        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .phone(registerRequest.getPhone())
                .role(Role.USER)
                .build();
        userRepository.save(user);

        emailService.sendEmailRegister(new Email(), registerRequest);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    //TODO metodo de registro para administradores;
    public AuthResponse registerAdmin(RegisterRequest registerRequest){
        User user = User.builder()
                .email(registerRequest.getEmail())
                .phone(registerRequest.getPhone())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.ADMINISTRATOR)
                .build();
        userRepository.save(user);
        emailService.sendEmailRegister(new Email(), registerRequest);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
