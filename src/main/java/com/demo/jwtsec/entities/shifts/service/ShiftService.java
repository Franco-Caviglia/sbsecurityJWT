package com.demo.jwtsec.entities.shifts.service;

import com.demo.jwtsec.entities.shifts.models.Shift;
import com.demo.jwtsec.entities.shifts.models.dtos.ShiftRequest;
import com.demo.jwtsec.entities.shifts.models.dtos.ShiftResponse;
import com.demo.jwtsec.entities.shifts.repository.ShiftRepository;
import com.demo.jwtsec.exceptions.ResourceNotFoundException;
import com.demo.jwtsec.loginjwt.auth.Repository.UserRepository;
import com.demo.jwtsec.loginjwt.auth.Requests.RegisterRequest;
import com.demo.jwtsec.loginjwt.auth.User.User;
import com.demo.jwtsec.mailsender.model.Email;
import com.demo.jwtsec.mailsender.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ShiftService {


    private final EmailService emailService;
    private final ShiftRepository shiftRepository;
    private final UserRepository userRepository;

    //TODO guardar el turno;
    public ResponseEntity<Shift> registerShift(ShiftRequest shiftRequest){
        String status = "pending";



        Shift shift = Shift.builder()
                .date(shiftRequest.getDate())
                .time(shiftRequest.getTime())
                .petName(shiftRequest.getPetName())
                .email(shiftRequest.getEmail())
                .disease(shiftRequest.getDisease())
                .build();
        shift.setStatus(status);

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

        return ResponseEntity.ok(shift);
    }

    public ResponseEntity<List<Shift>> getAllShifts(){
        return ResponseEntity.ok(shiftRepository.findAll());
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
