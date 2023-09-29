package com.demo.jwtsec.entities.clients.models;

import com.demo.jwtsec.entities.shifts.models.Shift;
import com.demo.jwtsec.loginjwt.auth.User.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customers", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class Customer implements UserDetails {
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


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
            @JoinTable(name = "customer-shifts",
                    joinColumns = @JoinColumn(name = "customer_id"),
                    inverseJoinColumns = @JoinColumn(name = "shift_id"))
    private List<Shift> shifts = new ArrayList<>();



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

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
}
