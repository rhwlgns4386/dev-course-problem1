package org.example.validator;

import org.example.global.exception.FormatException;

import java.util.regex.Pattern;

public final class NumberValidator {

    private NumberValidator() {}

    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");
    public static void validate(String value) throws IllegalArgumentException {
        if(!value.matches(NUMBER_PATTERN.pattern())){
            throw new FormatException("숫자가 아닙니다.");
        }
    }
}
