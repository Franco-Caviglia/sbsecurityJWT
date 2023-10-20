package com.demo.jwtsec.entities.clients.models.dtos;

import com.demo.jwtsec.entities.clients.models.Pets;
import com.demo.jwtsec.entities.shifts.models.Shift;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {

    private String username;
    private String email;
    private String phone;
    private List<Pets> pets = new ArrayList<>();
    private Set<Shift> shifts = new HashSet<>();

}
