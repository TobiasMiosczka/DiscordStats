package com.github.tobiasmiosczka.discordstats.rest.web.exception;

public class EmailNotFoundException extends Throwable{
    public EmailNotFoundException(String email) {
        super(email);
    }
}
