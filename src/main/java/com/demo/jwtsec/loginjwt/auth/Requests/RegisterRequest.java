package com.demo.jwtsec.loginjwt.auth.Requests;

import com.demo.jwtsec.loginjwt.auth.User.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    String username;
    String password;
    String email;
    String phone;
    List<Role> role;

}
