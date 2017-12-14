package com.github.tobiasmiosczka.discordstats.rest.web.exception;

@SuppressWarnings("serial")
public class UsernameExistsException extends Throwable {

    public UsernameExistsException(final String message) {
        super(message);
    }

}