package com.demo.jwtsec.auth.Response;


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
}
