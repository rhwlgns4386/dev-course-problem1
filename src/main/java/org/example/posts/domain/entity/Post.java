package org.example.posts.domain.entity;

import org.example.posts.domain.exeption.EntityCreationException;
import org.example.persistance.anotaion.Id;

import static org.example.validator.StringValidator.validate;

public class Post {

    @Id
    private Long id;
    private String title;
    private String content;

    public Post(Long id, String title, String content) throws EntityCreationException {
        this.id = id;
        setTitle(title);
        setContent(content);
    }

    private void setTitle(String title) {
        validate(title);
        this.title = title;
    }

    private void setContent(String content) {
        validate(content);
        this.content = content;
    }

    public Post(String title, String content) throws EntityCreationException {
        this(null, title, content);
    }


    public void update(String title, String content) {
        setTitle(title);
        setContent(content);
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
