package com.github.tobiasmiosczka.discordstats.web.exception;

public class EmailNotFoundException extends Throwable{
    public EmailNotFoundException(String email) {
        super(email);
    }
}
