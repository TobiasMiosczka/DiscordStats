package com.github.tobiasmiosczka.discordstats.rest.web.exception;

public class UnknownPasswordResetTokenException extends Throwable {
    public UnknownPasswordResetTokenException(final String message) {
        super(message);
    }
}
