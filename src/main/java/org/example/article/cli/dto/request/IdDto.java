package org.example.article.cli.dto.request;

public class IdDto {
    private String id;

    public IdDto(String id) {
        this.id = id;
    }

    public Long toLong() {
        return Long.valueOf(id.substring(0,id.length()-1));
    }
}
