package org.example.account.domain.entity;

import java.util.Objects;

public class UserName {

    private final String value;

    public UserName(String userName) {
        this.value = userName;
    }

    public String toStringValue() {
        return value;
    }
}
