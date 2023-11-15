package com.demo.jwtsec.entities.shifts.service;

import com.demo.jwtsec.entities.pets.models.Pets;
import com.demo.jwtsec.entities.pets.repository.PetRepository;
import com.demo.jwtsec.entities.pets.service.PetService;
import com.demo.jwtsec.entities.shifts.models.Shift;
import com.demo.jwtsec.entities.shifts.models.dtos.ShiftRequest;
import com.demo.jwtsec.entities.shifts.models.dtos.ShiftResponse;
import com.demo.jwtsec.entities.shifts.repository.ShiftRepository;
import com.demo.jwtsec.exceptions.ResourceNotFoundException;
import com.demo.jwtsec.loginjwt.auth.Repository.UserRepository;
import com.demo.jwtsec.loginjwt.auth.User.User;
import com.demo.jwtsec.mailsender.model.Email;
import com.demo.jwtsec.mailsender.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
@RequiredArgsConstructor
public class ShiftService {

    private final EmailService emailService;
    private final ShiftRepository shiftRepository;
    private final PetRepository petRepository;

    //TODO guardar el turno;
    public ShiftResponse registerShiftToPet(Long pet_id, ShiftRequest shiftRequest){
        String status = "Pendiente";

        Pets petId = petRepository.findById(pet_id).orElseThrow();

        Shift shift = Shift.builder()
                .pets(petId)
                .username(petId.getUser())
                .date(shiftRequest.getDate())
                .time(shiftRequest.getTime())
                .petName(petId.getPetName())
                .email(petId.getUser().getEmail())
                .disease(shiftRequest.getDisease())
                .build();
        shift.setStatus(status);
        shift.setPets(petId);


        LocalDateTime shiftTime = LocalDateTime.parse(shift.getDate() + "T" + shift.getTime());

        shift.setDateTime(shiftTime);


        //Buscar turno por fecha y hora (shiftTime) y verificar si esta pending o complete;


        shiftRepository.save(shift);
        //TODO configurar email con datos del turno;
        if( emailService != null){
            emailService.sendEmailShiftSubmit(new Email(), shiftRequest);
            emailService.sendEmailAdminShiftSubmit(new Email(), shiftRequest);
        }else {
            System.out.println("No funciono el mail sender");
        }

        return ShiftResponse.builder()
                .id(shift.getId())
                .date(shift.getDate())
                .username(shift.getUsername().getUsername())
                .disease(shift.getDisease())
                .email(shift.getEmail())
                .time(shift.getTime())
                .status(shift.getStatus())
                .petName(shift.getPetName())
                .build();
    }


    public List<ShiftResponse> getAllShifts(){
        List<Shift> shifts = shiftRepository.findAll();

        List<ShiftResponse> listShifts = new ArrayList<>();

        for(Shift shift : shifts){
            ShiftResponse shiftResponse = new ShiftResponse();
            shiftResponse.setId(shift.getId());
            shiftResponse.setPetName(shift.getPetName());
            shiftResponse.setDate(shift.getDate());
            shiftResponse.setTime(shift.getTime());
            shiftResponse.setUsername(shift.getUsername().getUsername());
            shiftResponse.setEmail(shift.getEmail());
            shiftResponse.setStatus(shift.getStatus());
            shiftResponse.setDisease(shift.getDisease());


            listShifts.add(shiftResponse);


        }

        return listShifts;
    }




    public ShiftResponse markCompleteShifts(Long id, ShiftResponse shiftResponse) {
        Shift shift =  shiftRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shift not found with id: " + id));

        shift.setStatus("completado");
        shift.setPets(new Pets());

        shiftRepository.save(shift);

        return ShiftResponse.builder()
                .username(shift.getUsername().getUsername())
                .date(shift.getDate())
                .petName(shift.getPetName())
                .disease(shift.getDisease())
                .email(shift.getEmail())
                .time(shift.getTime())
                .status(shift.getStatus())
                .id(shift.getId())
                .build();
    }

    public String deleteShift(Long id){

        Shift shift = shiftRepository.findById(id).orElseThrow();

        //Borramos la info de las  FKs para evitar problemas de claves huerfanas;
        shift.setPets(new Pets());
        shift.setUsername(new User());


        shiftRepository.deleteById(id);
        return "Turno con id: " + id + " eliminado";
    }

}
