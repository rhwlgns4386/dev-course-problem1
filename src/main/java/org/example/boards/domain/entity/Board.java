package org.example.boards.domain.entity;

import static org.example.article.domain.entity.StringValidator.validate;

public class Board {

    private Long id;
    private String title;

    public Board(String title) {
        validate(title);
        this.title = title;
    }

    public String title() {
        return title;
    }
}
