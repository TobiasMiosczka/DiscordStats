package com.github.tobiasmiosczka.discordstats.rest.events.listener;

import com.github.tobiasmiosczka.discordstats.rest.events.OnPasswordChangedEvent;
import com.github.tobiasmiosczka.discordstats.rest.model.platform.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class PasswordChangedListener implements ApplicationListener<OnPasswordChangedEvent> {

    final private JavaMailSender mailSender;

    @Autowired
    public PasswordChangedListener(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(OnPasswordChangedEvent onPasswordChangedEvent) {
        User user = onPasswordChangedEvent.getUser();

        String emailAddress = user.getEmail();
        String subject = "Password changed";
        String confirmationUrl = "Your password has been changed!";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailAddress);
        email.setSubject(subject);
        email.setText(confirmationUrl);
        mailSender.send(email);
    }
}
