package com.github.tobiasmiosczka.discordstats.rest.events;

import com.github.tobiasmiosczka.discordstats.rest.model.platform.PasswordResetToken;
import com.github.tobiasmiosczka.discordstats.rest.model.platform.User;
import org.springframework.context.ApplicationEvent;

public class OnRequestPasswordResetEvent extends ApplicationEvent {

    private User user;
    private PasswordResetToken passwordResetToken;

    public OnRequestPasswordResetEvent(User user, PasswordResetToken passwordResetToken) {
        super(user);
        this.passwordResetToken = passwordResetToken;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PasswordResetToken getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(PasswordResetToken token) {
        this.passwordResetToken = token;
    }
}
