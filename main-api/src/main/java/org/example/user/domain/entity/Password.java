package org.example.user.domain.entity;

import org.example.validator.StringValidator;

import java.util.Objects;

public class Password {

    private final String value;

    public Password(String password) {
        StringValidator.validate(password);
        this.value = password;
    }

    public String toStringValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
