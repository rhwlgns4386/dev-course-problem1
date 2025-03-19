package org.example.posts.domain.entity;

import org.example.boards.domain.entity.Board;
import org.example.posts.domain.exeption.EntityCreationException;
import org.example.persistance.anotaion.Id;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.example.validator.StringValidator.validate;

public class Post {

    @Id
    private Long id;
    private String title;
    private String content;
    private Board board;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Post(Long id, String title, String content,Board board) throws EntityCreationException {
        update(title,content);
        if(board == null){
            throw new IllegalArgumentException("값이 널이거나 비어있습니다.");
        }
        this.board = Objects.requireNonNull(board);
        this.id = id;
        LocalDateTime now = LocalDateTime.now();
        this.createdDate = now;
        this.updatedDate = now;
    }

    public Post(String title, String content,Board board) throws EntityCreationException {
        this(null, title, content,board);
    }


    public void update(String title, String content) {
        validate(title);
        validate(content);
        this.title = title;
        this.content = content;
        this.updatedDate = LocalDateTime.now();
    }

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }

    public Long id() {
        return id;
    }

    public Board board() {
        return board;
    }

    public LocalDateTime createdDate() {
        return createdDate;
    }

    public LocalDateTime updatedDate() {
        return updatedDate;
    }
}
