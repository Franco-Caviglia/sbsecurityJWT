package com.demo.jwtsec.loginjwt.auth.User.Response;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse{
    Long id;
    String username;
    String phone;
    String email;
    List<String> petNames;
}
