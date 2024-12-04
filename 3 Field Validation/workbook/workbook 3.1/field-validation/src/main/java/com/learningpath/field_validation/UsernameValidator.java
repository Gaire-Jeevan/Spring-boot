package com.learningpath.field_validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator implements ConstraintValidator<Username, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile("[^a-z0-9 ]");
        Matcher matcher = pattern.matcher(value);
//        boolean badCharacters = matcher.find(); //false if characters are a-z or 0-9


        return !(matcher.find());
    }
}
