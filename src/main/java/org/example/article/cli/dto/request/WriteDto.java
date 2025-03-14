package org.example.article.cli.dto.request;

public class WriteDto {

    private final String title;
    private final String content;

    public WriteDto(String title, String content) {
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
