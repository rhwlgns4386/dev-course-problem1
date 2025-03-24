package org.example.user.domain.entity;

import org.example.global.exception.FormatException;
import org.example.validator.StringValidator;

import java.util.regex.Pattern;

public class Email {

    private final String value;

    public Email(String value) throws FormatException{
        validationEmail(StringValidator.validate(value));
        this.value = value;
    }

    private static final Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private void validationEmail(String value) throws FormatException{
        if(!emailPattern.matcher(value).matches()){
            throw new FormatException("email 포멧일 일치하지 않습니다.");
        }
    }

    public String toStringValue() {
        return value;
    }
}
