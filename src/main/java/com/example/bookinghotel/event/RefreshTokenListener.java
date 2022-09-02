package com.example.bookinghotel.event;

import com.example.bookinghotel.enity.User;
import com.example.bookinghotel.enity.VerificationToken;
import com.example.bookinghotel.service.serviceImpl.VerificationTokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class RefreshTokenListener implements ApplicationListener<RefreshTokenEvent> {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VerificationTokenServiceImpl verificationTokenServiceImpl;

    @Override
    public void onApplicationEvent(RefreshTokenEvent event) {

        this.confirmRegistration(event);

    }

    private void confirmRegistration(RefreshTokenEvent event) {

        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = verificationTokenServiceImpl.findVerificationTokenByToken(event.getToken());
        verificationToken.setToken(token);
        verificationToken.setExpiryDate(LocalDateTime.now().plusMinutes(60));
        verificationTokenServiceImpl.createToken(verificationToken);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                = "/registrationConfirm?token=" + token;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(event.getLocale() + "\r\n" + "Click on the link to authenticate " +"\r\n"+ "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }
}
