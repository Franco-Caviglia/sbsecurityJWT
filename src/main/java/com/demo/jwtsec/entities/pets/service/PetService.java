package com.demo.jwtsec.entities.pets.service;

import com.demo.jwtsec.entities.pets.models.Pets;
import com.demo.jwtsec.entities.pets.models.dtos.PetRequest;
import com.demo.jwtsec.entities.pets.models.dtos.PetResponse;
import com.demo.jwtsec.entities.pets.repository.PetRepository;
import com.demo.jwtsec.exceptions.ResourceNotFoundException;
import com.demo.jwtsec.loginjwt.auth.Repository.UserRepository;
import com.demo.jwtsec.loginjwt.auth.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;

    public Pets addPetProfile(PetRequest petRequest){

        Pets pet = Pets.builder()
                .petName(petRequest.getPetName())
                .petAge(petRequest.getPetAge())
                .petType(petRequest.getPetType())
                .breed(petRequest.getBreed())
                .build();

        petRepository.save(pet);

        return pet;
    }

    public List<Pets> getAllPets() {
        return petRepository.findAll();
    }

    public Pets editPet(Long id, PetResponse petResponse) {
        Pets pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shift not found with id: " + id));

        pet.setPetName(petResponse.getPetName());
        pet.setPetAge(petResponse.getPetAge());

        pet.setPetType(petResponse.getPetType());
        pet.setBreed(petResponse.getBreed());

        petRepository.save(pet);

        return pet;
    }
}
