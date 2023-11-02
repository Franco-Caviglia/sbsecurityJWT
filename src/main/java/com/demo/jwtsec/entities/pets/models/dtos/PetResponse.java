package com.demo.jwtsec.entities.pets.models.dtos;

import com.demo.jwtsec.loginjwt.auth.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetResponse {
    private Long petId;
    private String petName;
    private String breed;//raza del animal;
    private String petType;//tipo de animal;
    private Integer petAge;
    private String username;

}
