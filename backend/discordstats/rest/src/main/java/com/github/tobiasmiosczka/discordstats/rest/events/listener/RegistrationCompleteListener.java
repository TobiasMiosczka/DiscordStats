package com.github.tobiasmiosczka.discordstats.rest.events.listener;

import com.github.tobiasmiosczka.discordstats.rest.events.OnRegistrationCompleteEvent;
import com.github.tobiasmiosczka.discordstats.rest.model.platform.User;
import com.github.tobiasmiosczka.discordstats.rest.web.controller.RegistrationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class RegistrationCompleteListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    final private JavaMailSender mailSender;

    @Autowired
    public RegistrationCompleteListener(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
        User user = onRegistrationCompleteEvent.getRegisteredUser();

        String emailAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = "http://localhost" + RegistrationController.URL_REGISTRATION_CONFIRM + "?token=" + onRegistrationCompleteEvent.getVerificationToken().getToken();

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailAddress);
        email.setSubject(subject);
        email.setText(confirmationUrl);
        mailSender.send(email);
    }
}