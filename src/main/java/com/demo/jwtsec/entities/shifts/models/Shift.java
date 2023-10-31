package com.demo.jwtsec.entities.shifts.models;

import com.demo.jwtsec.entities.pets.models.Pets;
import com.demo.jwtsec.loginjwt.auth.User.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "shifts")
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pet_name")
    private String petName;

    @Column(name = "date")
    private LocalDate date;

    @OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "username", referencedColumnName = "username")
    private Pets pets;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String email;//email de confirmacion;

    @Column(name = "time")
    private LocalTime time;

    private String disease;

    private String status;

    @Column(unique = true)
    private LocalDateTime dateTime;

}
