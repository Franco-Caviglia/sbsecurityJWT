package com.demo.jwtsec.loginjwt.auth.User;

import com.demo.jwtsec.entities.clients.models.Pets;
import com.demo.jwtsec.entities.shifts.models.Shift;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "\"users\"", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails, GrantedAuthority {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false)
    String username;
    String password;
    String phone;
    String email;
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Pets.class)
    List<Pets> pets;

    @OneToMany
    Set<Shift> shifts = new HashSet<>();
    @Enumerated(EnumType.STRING)
    Role role;

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
