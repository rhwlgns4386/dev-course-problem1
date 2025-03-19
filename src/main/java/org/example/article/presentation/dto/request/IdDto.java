package org.example.article.presentation.dto.request;

import static org.example.validator.StringValidator.validate;

public class IdDto {
    private final String id;

    public IdDto(String id) {
        this.id = id;
    }

    public Long toLong() {
        validate(id);
        return Long.valueOf(id.substring(0,id.length()-1));
    }
}
