package com.demo.jwtsec.loginjwt.auth.Response;



import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



//Respuesta que devuelve el token, no importa si es registro o login;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    String token;

    @Enumerated(EnumType.STRING)
    String authorities;

    String username;
}
