package org.example.boards.domain.entity;

import java.time.LocalDateTime;

public class Title {

    private final String value;
    private final LocalDateTime createAt;

    public Title(String value) {
        this.value = value;
        this.createAt = LocalDateTime.now();
    }

    public String toStringValue() {
        return value;
    }

    public LocalDateTime createAt() {
        return createAt;
    }
}
