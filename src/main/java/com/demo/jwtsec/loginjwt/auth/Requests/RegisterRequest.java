package com.demo.jwtsec.loginjwt.auth.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    String username;
    String password;
    String email;
    String phone;

}
