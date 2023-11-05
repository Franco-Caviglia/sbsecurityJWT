package com.demo.jwtsec.mailsender.service;

import com.demo.jwtsec.entities.shifts.models.dtos.ShiftRequest;
import com.demo.jwtsec.loginjwt.auth.Repository.UserRepository;
import com.demo.jwtsec.loginjwt.auth.Requests.RegisterRequest;
import com.demo.jwtsec.loginjwt.auth.Response.AuthResponse;
import com.demo.jwtsec.loginjwt.auth.User.User;
import com.demo.jwtsec.mailsender.model.Email;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendEmailRegister(Email email, @RequestBody RegisterRequest registerRequest){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("Huesitos <"+sender+">");
            mailMessage.setTo(registerRequest.getEmail());
            mailMessage.setSubject("Registro exitoso");
            mailMessage.setText("Bienvenido a nuestro sitio web!");

            javaMailSender.send(mailMessage);
        } catch (Exception e){
        }
    }

    public void sendEmailShiftSubmit(Email email, @RequestBody ShiftRequest shiftRequest) {
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("Huesitos <"+sender+">");
            mailMessage.setTo(String.valueOf(new InternetAddress(shiftRequest.getEmail())));
            mailMessage.setSubject("Turno pedido por: " + shiftRequest.getDisease() + " registrado");
            mailMessage.setText("El turno para " + shiftRequest.getPetName() + " solicitado para el dia "
                    + shiftRequest.getDate() +
                    "en el horario "+ shiftRequest.getTime() + "ha sido creado con exito. Te esperamos!!!");

            javaMailSender.send(mailMessage);
        } catch (MailException | AddressException e){


        }
    }

    public void sendEmailAdminShiftSubmit(Email email, @RequestBody ShiftRequest shiftRequest) {

        List<User> users = userRepository.findAll();

        List<String> emails = users.stream()
                .filter(user -> user.getRole().toString().equals("ADMINISTRATOR"))
                .distinct()
                .map(User::getEmail).collect(Collectors.toList());

        String emailAddresses = String.join(" , ", emails);
        System.out.println(emailAddresses);
        if(emails.isEmpty()){
            emails.add("francocaviglia77@gmail.com");
        }


        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom("Huesitos <" + sender + ">");

            //Convertimos en array nuestra lista para que pueda recorrerla nuestro setTo();


            mailMessage.setTo(emailAddresses);
            mailMessage.setSubject("Turno pedido por: " + shiftRequest.getDisease() + " registrado");
            mailMessage.setText("El turno para " + shiftRequest.getPetName() + " solicitado para el dia "
                    + shiftRequest.getDate() +
                    " en el horario " + shiftRequest.getTime() + " ha sido creado con exito. Te esperamos!!!");

            javaMailSender.send(mailMessage);

        } catch (MailException e) {
        }
    }
}
