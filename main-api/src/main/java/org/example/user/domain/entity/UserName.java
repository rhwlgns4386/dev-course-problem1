package org.example.user.domain.entity;

import java.util.Objects;

public class UserName {

    private final String value;

    public UserName(String userName) {
        this.value = userName;
    }

    public String toStringValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserName userName = (UserName) o;
        return Objects.equals(value, userName.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
