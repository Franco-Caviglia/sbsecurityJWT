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

    public PetResponse addPetProfileFromAdmin(Long id, PetRequest petRequest){

        Optional<User> userid = Optional.of(userRepository.findById(id).get());

        Pets pet = Pets.builder()
                .petName(petRequest.getPetName())
                .petAge(petRequest.getPetAge())
                .petType(petRequest.getPetType())
                .breed(petRequest.getBreed())
                .user(userid.get()) //relacion establecida con la tabla users;
                .build();

        petRepository.save(pet);

        return PetResponse.builder()
                .petId(pet.getId())
                .petName(pet.getPetName())
                .petType(pet.getPetType())
                .breed(pet.getBreed())
                .petAge(pet.getPetAge())
                .username(userid.get().getUsername())
                .build();
    }

    public List<PetResponse> getUserPets(Long userid) {

        List<Pets> petsByUserId = petRepository.findByUserId(userid);

        List<PetResponse> listPets = new ArrayList<>();

        for(Pets pet : petsByUserId) {
            PetResponse petResponse = new PetResponse();
            petResponse.setPetId(pet.getId());
            petResponse.setPetName(pet.getPetName());
            petResponse.setPetAge(pet.getPetAge());
            petResponse.setBreed(pet.getBreed());
            petResponse.setPetType(pet.getPetType());
            petResponse.setUsername(pet.getUser().getUsername());
            listPets.add(petResponse);
        }
        return listPets;
    }


    public PetResponse editPet(Long id, PetResponse petResponse) {
        Pets pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shift not found with id: " + id));

        pet.setPetName(petResponse.getPetName());
        pet.setPetAge(petResponse.getPetAge());

        pet.setPetType(petResponse.getPetType());
        pet.setBreed(petResponse.getBreed());

        petRepository.save(pet);

        return  PetResponse.builder()
                .petId(pet.getId())
                .petName(pet.getPetName())
                .petType(pet.getPetType())
                .breed(pet.getBreed())
                .petAge(pet.getPetAge())
                .username(pet.getUser().getUsername())
                .build();
    }
}
