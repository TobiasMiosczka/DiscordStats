package com.github.tobiasmiosczka.discordstats.web.exception;

public class UnknownEmailVerificationTokenException extends Throwable {
    public UnknownEmailVerificationTokenException(final String message) {
        super(message);
    }
}
