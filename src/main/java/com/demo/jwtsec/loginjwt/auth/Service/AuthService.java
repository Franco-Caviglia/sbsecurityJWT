package com.demo.jwtsec.loginjwt.auth.Service;

import com.demo.jwtsec.entities.admins.models.Admin;
import com.demo.jwtsec.entities.admins.repository.AdminRepository;
import com.demo.jwtsec.entities.clients.models.Customer;
import com.demo.jwtsec.entities.clients.repository.CustomerRepository;
import com.demo.jwtsec.loginjwt.auth.Jwt.Service.JWTService;
import com.demo.jwtsec.loginjwt.auth.Repository.UserRepository;
import com.demo.jwtsec.loginjwt.auth.User.Role;
import com.demo.jwtsec.loginjwt.auth.Requests.LoginRequest;
import com.demo.jwtsec.loginjwt.auth.Requests.RegisterRequest;
import com.demo.jwtsec.loginjwt.auth.Response.AuthResponse;
import com.demo.jwtsec.mailsender.model.Email;
import com.demo.jwtsec.mailsender.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    public AuthResponse login(LoginRequest loginRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        UserDetails user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }


    public AuthResponse registerUser(RegisterRequest registerRequest) {
        Customer customer = Customer.builder()
                .email(registerRequest.getEmail())
                .phone(registerRequest.getPhone())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        customerRepository.save(customer);
        emailService.sendEmailRegister(new Email(), registerRequest);

        return AuthResponse.builder()
                .token(jwtService.getToken(customer))
                .build();
    }

    //TODO metodo de registro para administradores;
    public AuthResponse registerAdmin(RegisterRequest registerRequest){
        Admin admin = Admin.builder()
                .email(registerRequest.getEmail())
                .phone(registerRequest.getPhone())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.ADMINISTRATOR)
                .build();
        adminRepository.save(admin);
        emailService.sendEmailRegister(new Email(), registerRequest);

        return AuthResponse.builder()
                .token(jwtService.getToken(admin))
                .build();
    }
}
