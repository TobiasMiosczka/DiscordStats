package com.github.tobiasmiosczka.discordstats.events.listener;

import com.github.tobiasmiosczka.discordstats.events.OnRegistrationCompleteEvent;
import com.github.tobiasmiosczka.discordstats.model.platform.User;
import com.github.tobiasmiosczka.discordstats.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class RegistrationCompleteListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    final private IUserService userService;
    final private JavaMailSender mailSender;

    @Autowired
    public RegistrationCompleteListener(IUserService userService, JavaMailSender mailSender) {
        this.userService = userService;
        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
        User user = onRegistrationCompleteEvent.getRegisteredUser();

        String emailAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = "http://localhost/registration/confirm?token=" + onRegistrationCompleteEvent.getVerificationToken().getToken();

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailAddress);
        email.setSubject(subject);
        email.setText(confirmationUrl);
        mailSender.send(email);
    }
}