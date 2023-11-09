package com.demo.jwtsec.loginjwt.auth.Service;

import com.demo.jwtsec.entities.pets.models.Pets;
import com.demo.jwtsec.entities.pets.models.dtos.PetResponse;
import com.demo.jwtsec.entities.pets.repository.PetRepository;
import com.demo.jwtsec.loginjwt.auth.Repository.UserRepository;
import com.demo.jwtsec.loginjwt.auth.User.Response.UserResponse;
import com.demo.jwtsec.loginjwt.auth.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserResponse> listUsers = new ArrayList<>();



        for(User user : users){

            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setUsername(user.getUsername());
            userResponse.setPhone(user.getPhone());
            userResponse.setEmail(user.getEmail());

            List<Pets> listPets = petRepository.findByUserId(user.getId());
            List<String> listPetsNames = new ArrayList<>();

            for(Pets pet : listPets){
                listPetsNames.add(pet.getPetName());
            }

            userResponse.setPetNames(listPetsNames);
            listUsers.add(userResponse);
        }

        return listUsers;
    }
}
