package com.github.tobiasmiosczka.discordstats.events.listener;

import com.github.tobiasmiosczka.discordstats.events.OnUserVerifiedEvent;
import com.github.tobiasmiosczka.discordstats.model.platform.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class UserVerifiedListener implements ApplicationListener<OnUserVerifiedEvent> {

    final private JavaMailSender mailSender;

    @Autowired
    public UserVerifiedListener(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(OnUserVerifiedEvent onUserVerifiedEvent) {
        User user = onUserVerifiedEvent.getUser();
        String emailAddress = user.getEmail();
        String subject = "Verified";
        String confirmationUrl = "You have been verified!";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailAddress);
        email.setSubject(subject);
        email.setText(confirmationUrl);
        mailSender.send(email);
    }
}
