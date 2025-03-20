package org.example.account.domain.entity;

import org.example.validator.StringValidator;

public class Password {

    private final String value;

    public Password(String password) {
        StringValidator.validate(password);
        this.value = password;
    }

    public String toStringValue() {
        return value;
    }
}
