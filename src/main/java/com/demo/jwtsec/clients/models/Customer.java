package com.demo.jwtsec.clients.models;

import com.demo.jwtsec.loginjwt.auth.User.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customers", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String phone;

    private Role role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
            @JoinTable( name = "customer-pets",
                    joinColumns = @JoinColumn(name = "customer_id"),
                    inverseJoinColumns = @JoinColumn(name = "pet_id"))
    private List<Pets> pets = new ArrayList<>();

}
