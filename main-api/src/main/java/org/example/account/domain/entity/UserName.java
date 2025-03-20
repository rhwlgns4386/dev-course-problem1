package org.example.account.domain.entity;

public class UserName {

    private final String value;

    public UserName(String testId) {
        this.value = testId;
    }


    public String toStringValue() {
        return value;
    }
}
