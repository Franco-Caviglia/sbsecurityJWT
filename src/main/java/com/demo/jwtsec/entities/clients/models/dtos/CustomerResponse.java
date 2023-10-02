package com.demo.jwtsec.entities.clients.models.dtos;

import com.demo.jwtsec.entities.clients.models.Pets;
import com.demo.jwtsec.entities.shifts.models.Shift;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {

    private String username;
    private String email;
    private String phone;
    private List<Pets> pets = new ArrayList<>();
    private List<Shift> shifts = new ArrayList<>();

}
