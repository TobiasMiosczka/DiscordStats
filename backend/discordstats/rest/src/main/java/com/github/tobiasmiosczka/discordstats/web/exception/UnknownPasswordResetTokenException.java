package com.github.tobiasmiosczka.discordstats.web.exception;

public class UnknownPasswordResetTokenException extends Throwable {
    public UnknownPasswordResetTokenException(final String message) {
        super(message);
    }
}
