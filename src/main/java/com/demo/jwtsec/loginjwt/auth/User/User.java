package com.demo.jwtsec.loginjwt.auth.User;

import com.demo.jwtsec.entities.pets.models.Pets;
import com.demo.jwtsec.entities.shifts.models.Shift;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "\"users\"", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails, GrantedAuthority {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String username;
    String password;
    String phone;
    String email;
    @Enumerated(EnumType.STRING)
    Role role;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    //List<Pets> pets;

    /*
    //=====================================
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Shift> shifts;
    //====================================
    */



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    //Estos metodos los manejamos a traves del JWT, por lo que no es necesario configurarlos;
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getAuthority() {
        return role.name();
    }
}
