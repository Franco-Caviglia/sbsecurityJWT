package com.demo.jwtsec.entities.clients.service;

import com.demo.jwtsec.entities.clients.models.Pets;
import com.demo.jwtsec.entities.clients.models.dtos.CustomerResponse;
import com.demo.jwtsec.entities.clients.models.dtos.PetRequest;
import com.demo.jwtsec.entities.clients.models.dtos.PetResponse;
import com.demo.jwtsec.entities.clients.repository.PetRepository;
import com.demo.jwtsec.entities.shifts.models.Shift;
import com.demo.jwtsec.exceptions.ResourceNotFoundException;
import com.demo.jwtsec.loginjwt.auth.Repository.UserRepository;
import com.demo.jwtsec.loginjwt.auth.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;
    public User getCustomerProfileById(Integer id, CustomerResponse customerResponse) {
        User customer = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        customer.setEmail(customerResponse.getEmail());
        customer.setPets(customerResponse.getPets());
        customer.setPhone(customerResponse.getPhone());
        customer.setUsername(customerResponse.getUsername());
        customer.setShifts(customerResponse.getShifts());

        return customer;
    }


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
