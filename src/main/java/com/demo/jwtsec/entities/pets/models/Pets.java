package com.demo.jwtsec.entities.pets.models;


import com.demo.jwtsec.entities.shifts.models.Shift;
import com.demo.jwtsec.loginjwt.auth.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pets")
public class Pets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String petName;
    private String breed;//raza del animal;
    private String petType;//tipo de animal;
    private Integer petAge;

    @ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "username")
    private User user;

    @OneToOne(mappedBy = "pets")
    private Shift shiftDetails;

}
