package com.demo.jwtsec.entities.shifts.service;

import com.demo.jwtsec.entities.shifts.models.Shift;
import com.demo.jwtsec.entities.shifts.models.dtos.ShiftRequest;
import com.demo.jwtsec.entities.shifts.repository.ShiftRepository;
import com.demo.jwtsec.mailsender.model.Email;
import com.demo.jwtsec.mailsender.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class ShiftService {

    private EmailService emailService;
    @Autowired
    private ShiftRepository shiftRepository;

    //TODO guardar el turno;
    public ResponseEntity<Shift> registerShift(ShiftRequest shiftRequest){
        Shift shift = Shift.builder()
                .date(shiftRequest.getDate())
                .time(shiftRequest.getTime())
                .petName(shiftRequest.getPetName())
                .build();

        LocalDateTime shiftTime = LocalDateTime.parse(shift.getDate() + "T" + shift.getTime());
        shift.setDateTime(shiftTime);

        shiftRepository.save(shift);
        //TODO configurar email con datos del turno;
        //emailService.sendEmailShiftSubmit(new Email(), shiftRequest);
        return ResponseEntity.ok(shift);
    }



}
