package org.example.article.domain.entity;

import org.example.article.domain.exeption.EntityCreationException;
import org.example.article.persistance.anotaion.Id;

import static org.example.article.domain.entity.StringValidator.validate;

public class Article {

    @Id
    private Long id;
    private String title;
    private String content;

    public Article(Long id, String title, String content) throws EntityCreationException {
        validate(title);
        validate(content);
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Article(String title, String content) throws EntityCreationException {
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
