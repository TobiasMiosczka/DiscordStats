package com.github.tobiasmiosczka.discordstats.web.exception;

public class EmailVerificationTokenExpiredException extends Throwable {

    public EmailVerificationTokenExpiredException(final String message) {
        super(message);
    }
}
