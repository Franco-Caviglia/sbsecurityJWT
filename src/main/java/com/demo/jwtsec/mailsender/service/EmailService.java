package com.demo.jwtsec.mailsender.service;

import com.demo.jwtsec.loginjwt.auth.Requests.RegisterRequest;
import com.demo.jwtsec.mailsender.model.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendEmailRegister(Email email, @RequestBody RegisterRequest registerRequest){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("Huesitos <"+sender+">");
            mailMessage.setTo(registerRequest.getEmail());
            mailMessage.setSubject("Registro exitoso");
            mailMessage.setText("Bienvenido a nuestro sitio web!");

            javaMailSender.send(mailMessage);
            return "Email sent successfully";
        } catch (Exception e){
            return "Email sending error";
        }
    }

}
