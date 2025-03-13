package org.example.article.domain.entity;

import java.util.Objects;

public class Article {

    private Long id;
    private String title;
    private String content;

    public Article(Long id, String title, String content) {
        validate(title,content);
        this.id = id;
        this.title = title;
        this.content = content;
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

    public Article(String title, String content) {
        this(null, title, content);
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
