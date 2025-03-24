package org.example.boards.domain.entity;

import org.example.persistance.anotaion.Id;

import java.time.LocalDateTime;

import static org.example.validator.StringValidator.validate;

public class Board {

    @Id
    private Long id;
    private String title;
    private LocalDateTime updateAt;

    public Board(Title title) {
        setTitle(title);
    }


    private void setTitle(Title title) {
        validate(title.toStringValue());
        this.title = title.toStringValue();
        this.updateAt = title.createAt();
    }

    public String title() {
        return title;
    }

    public void update(Title title) {
        setTitle(title);
    }

    public Long id() {
        return id;
    }

    public LocalDateTime updateAt() {
        return updateAt;
    }
}
