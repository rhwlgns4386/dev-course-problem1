package org.example.boards.domain.entity;

public class Board {

    private Long id;
    private String title;

    public Board(String title) {
        this.title = title;
    }

    public String title() {
        return title;
    }
}
