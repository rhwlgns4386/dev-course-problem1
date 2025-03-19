package org.example.posts.presentation.dto.request;

public class PostInfoDto {

    private final String title;
    private final String content;

    public PostInfoDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }
}
