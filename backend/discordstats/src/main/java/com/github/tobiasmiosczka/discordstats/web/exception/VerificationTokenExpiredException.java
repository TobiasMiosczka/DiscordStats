package com.github.tobiasmiosczka.discordstats.web.exception;

import java.util.Date;

public class VerificationTokenExpiredException extends Throwable {

    public VerificationTokenExpiredException(final String token, final Date expiryDate) {
        super(token + " " + expiryDate);
    }
}
