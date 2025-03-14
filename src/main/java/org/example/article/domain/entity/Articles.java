package org.example.article.domain.entity;

import java.util.List;
import java.util.Objects;

public class Articles {
    private final List<Article> articles;

    public Articles(List<Article> articles) {
        this.articles = articles;
    }

    public Integer size() {
        return articles.size();
    }

    public List<Article> articles() {
        return articles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Articles articles1 = (Articles) o;
        for (Article article : articles) {
            if(!articles1.articles.contains(article)){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(articles);
    }
}
