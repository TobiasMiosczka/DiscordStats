package com.github.tobiasmiosczka.discordstats.events;

import com.github.tobiasmiosczka.discordstats.model.platform.User;
import com.github.tobiasmiosczka.discordstats.model.platform.VerificationToken;
import org.springframework.context.ApplicationEvent;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private User user;
    private VerificationToken verificationToken;

    public OnRegistrationCompleteEvent(User user, VerificationToken verificationToken) {
        super(user);
        this.user = user;
        this.verificationToken = verificationToken;
    }

    public User getRegisteredUser() {
        return user;
    }

    public void setUser(User registeredUser) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public VerificationToken getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(VerificationToken verificationToken) {
        this.verificationToken = verificationToken;
    }
}
