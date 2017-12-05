package com.github.tobiasmiosczka.discordstats.events;

import com.github.tobiasmiosczka.discordstats.model.platform.User;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private User registeredUser;
    private Locale locale;
    private String appUrl;

    public OnRegistrationCompleteEvent(User registeredUser, Locale locale, String appUrl) {
        super(registeredUser);
        this.registeredUser = registeredUser;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    public User getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(User registeredUser) {
        this.registeredUser = registeredUser;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }
}
