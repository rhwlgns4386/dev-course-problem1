package org.example.posts.presentation.dto.request;

import static org.example.validator.StringValidator.validate;

public class IdDto {
    private final String id;

    public IdDto(String id) {
        this.id = id;
    }

    public Long toLong() {
        validate(id);
        return Long.valueOf(id);
    }
}
