package com.github.tobiasmiosczka.discordstats.events.listener;

import com.github.tobiasmiosczka.discordstats.events.OnRequestPasswordResetEvent;
import com.github.tobiasmiosczka.discordstats.model.platform.User;
import com.github.tobiasmiosczka.discordstats.web.controller.RegistrationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class RequestPasswordResetListener implements ApplicationListener<OnRequestPasswordResetEvent> {

    final private JavaMailSender mailSender;

    @Autowired
    public RequestPasswordResetListener(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(OnRequestPasswordResetEvent onRequestPasswordResetEvent) {
        User user = onRequestPasswordResetEvent.getUser();

        String emailAddress = user.getEmail();
        String subject = "Password reset";
        String confirmationUrl = "http://localhost" + RegistrationController.URL_RESET_PASSWORD
                + "?id=" + user.getId()
                + "&token=" + onRequestPasswordResetEvent.getPasswordResetToken().getToken()
                + "\n Token: " + onRequestPasswordResetEvent.getPasswordResetToken().getToken();

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailAddress);
        email.setSubject(subject);
        email.setText(confirmationUrl);
        mailSender.send(email);
    }
}
