package com.github.tobiasmiosczka.discordstats.rest.web.dto.validation.validators;

import com.github.tobiasmiosczka.discordstats.rest.web.dto.validation.ValidName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator implements ConstraintValidator<ValidName, String> {

    private static final String NAME_PATTERN = "[A-Z][a-z]*$";
    private static final Pattern pattern = Pattern.compile(NAME_PATTERN);

    @Override
    public void initialize(ValidName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context){
        return (validateName(name));
    }

    private boolean validateName(String name) {
        if (name == null ||name.isEmpty())
            return true;

        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}