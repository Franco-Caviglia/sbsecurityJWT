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
import com.demo.jwtsec.mailsender.model.Email;
import com.demo.jwtsec.mailsender.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ShiftService {

    private final PetService petService;
    private final EmailService emailService;
    private final ShiftRepository shiftRepository;
    private final PetRepository petRepository;
    private final UserRepository userRepository;

    //TODO guardar el turno;
    public ShiftResponse registerShiftToPet(Long pet_id, ShiftRequest shiftRequest){
        String status = "pending";

        Pets petId = petRepository.findById(pet_id).orElseThrow();

        Shift shift = Shift.builder()

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

        shiftRepository.save(shift);
        //TODO configurar email con datos del turno;
        if( emailService != null){
            emailService.sendEmailShiftSubmit(new Email(), shiftRequest);
            emailService.sendEmailAdminShiftSubmit(new Email(), shiftRequest);
        }else {
            System.out.println("No funciono el mail sender");
        }

        return ShiftResponse.builder()
                .date(shift.getDate())
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
            shiftResponse.setPetName(shift.getPetName());
            shiftResponse.setDate(shift.getDate());
            shiftResponse.setTime(shift.getTime());
            shiftResponse.setEmail(shift.getEmail());
            shiftResponse.setStatus(shift.getStatus());
            shiftResponse.setDisease(shift.getDisease());

            listShifts.add(shiftResponse);
        }

        return listShifts;
    }




    public Shift markCompleteShifts(Long id, ShiftResponse shiftResponse) {
        Shift shift =  shiftRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shift not found with id: " + id));

        shift.setStatus(shiftResponse.getStatus());

        return shiftRepository.save(shift);
    }

    public Map<String, Boolean> deleteShift(Long id) {
        Shift shift = shiftRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("shift not found"));

        shiftRepository.delete(shift);
        Map<String, Boolean> answer = new HashMap<>();
        answer.put("delete", Boolean.TRUE);
        return answer;
    }

    public Shift editShifts(Long id, ShiftResponse shiftResponse) {
        Shift shift = shiftRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shift not found with id: " + id));

        shift.setPetName(shiftResponse.getPetName());
        shift.setEmail(shiftResponse.getEmail());
        shift.setDisease(shiftResponse.getDisease());
        shift.setDate(shiftResponse.getDate());
        shift.setTime(shiftResponse.getTime());

        LocalDateTime shiftTime = LocalDateTime.parse(shift.getDate() + "T" + shift.getTime());
        shift.setDateTime(shiftTime);

        shiftRepository.save(shift);
        return shift;
    }
}
