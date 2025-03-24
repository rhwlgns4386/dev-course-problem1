package org.example.boards.presentation.command;

import org.example.validator.NumberValidator;
import org.example.validator.StringValidator;

public class ValidationLongConverter {

    public static Long convert(String value) {
        StringValidator.validate(value);
        NumberValidator.validate(value);
        return Long.parseLong(value);
    }
}
