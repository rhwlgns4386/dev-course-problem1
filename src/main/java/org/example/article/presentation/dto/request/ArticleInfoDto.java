package org.example.article.presentation.dto.request;

public class ArticleInfoDto {

    private final String title;
    private final String content;

    public ArticleInfoDto(String title, String content) {
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
