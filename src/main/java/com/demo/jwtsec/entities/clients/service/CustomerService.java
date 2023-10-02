package com.demo.jwtsec.entities.clients.service;

import com.demo.jwtsec.entities.clients.models.Customer;
import com.demo.jwtsec.entities.clients.models.Pets;
import com.demo.jwtsec.entities.clients.models.dtos.CustomerResponse;
import com.demo.jwtsec.entities.clients.models.dtos.PetRequest;
import com.demo.jwtsec.entities.clients.models.dtos.PetResponse;
import com.demo.jwtsec.entities.clients.repository.CustomerRepository;
import com.demo.jwtsec.entities.clients.repository.PetRepository;
import com.demo.jwtsec.entities.shifts.models.Shift;
import com.demo.jwtsec.entities.shifts.models.dtos.ShiftResponse;
import com.demo.jwtsec.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;
    public Customer getCustomerProfileById(Long id, CustomerResponse customerResponse) {
        Customer customer = customerRepository.findById(id)
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
