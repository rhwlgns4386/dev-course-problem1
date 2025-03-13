package org.example.article.domain.entity;

import org.example.article.domain.exeption.EntityCreationException;
import org.example.article.persistance.anotaion.Id;

import java.util.Objects;

public class Article {

    @Id
    private Long id;
    private String title;
    private String content;

    public Article(Long id, String title, String content) throws EntityCreationException {
        validate(title,content);
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Article(String title, String content) throws EntityCreationException {
        this(null, title, content);
    }

    private void validate(String title, String content) throws IllegalArgumentException {
        try {
            if(isEmpty(Objects.requireNonNull(title)) ||  isEmpty(Objects.requireNonNull(content))) {
                throw new IllegalArgumentException("title or content must not be empty");
            }
        }catch (NullPointerException e) {
            throw new IllegalArgumentException("title or content must not be null");
        }
    }

    private boolean isEmpty(String value) {
        return value.isBlank();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
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
}
