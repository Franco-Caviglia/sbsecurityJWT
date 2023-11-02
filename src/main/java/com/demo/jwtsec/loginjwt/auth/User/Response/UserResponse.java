package com.demo.jwtsec.loginjwt.auth.User.Response;

import com.demo.jwtsec.entities.pets.models.Pets;
import com.demo.jwtsec.entities.shifts.models.Shift;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse{
    Long id;
    String username;
    String password;
    String phone;
    String email;
    List<Pets> pets;
    List<Shift> shifts;
}
