package org.example.article.domain.entity;

import java.util.Objects;

public final class StringValidator {

    public static void validate(String value) throws IllegalArgumentException {
        try {
            if (isEmpty(Objects.requireNonNull(value))) {
                throw exception();
            }
        } catch (NullPointerException e) {
            throw exception();
        }
    }

    private static boolean isEmpty(String value) {
        return value.isBlank();
    }

    private static IllegalArgumentException exception() {
        return new IllegalArgumentException("값이 널이거나 비어있습니다.");
    }
}