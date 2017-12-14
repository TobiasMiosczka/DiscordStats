package com.github.tobiasmiosczka.discordstats.rest.web.dto.validation.validators;

import com.github.tobiasmiosczka.discordstats.rest.web.dto.validation.ValidUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]*$";
    private static final Pattern pattern = Pattern.compile(USERNAME_PATTERN);

    @Override
    public void initialize(ValidUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context){
        return (validateUsername(username));
    }

    private boolean validateUsername(String username) {
        if (username == null ||username.isEmpty())
            return false;

        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
}