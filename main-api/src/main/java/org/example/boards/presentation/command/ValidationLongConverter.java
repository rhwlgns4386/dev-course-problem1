package org.example.boards.presentation.command;

import org.example.global.exception.FormatException;
import org.example.validator.NumberValidator;

import java.util.Objects;

public class ValidationLongConverter {

    public static Long convert(String value) {
        NumberValidator.validate(Objects.requireNonNull(value));
        return Long.parseLong(value);
    }
}
