package com.github.tobiasmiosczka.discordstats.web.exception;

@SuppressWarnings("serial")
public class UsernameExistsException extends RuntimeException {

    public UsernameExistsException(final String message) {
        super(message);
    }

}