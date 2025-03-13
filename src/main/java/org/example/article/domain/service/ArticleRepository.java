package org.example.article.domain.service;

import org.example.article.domain.entity.Article;

import java.util.List;

public interface ArticleRepository {
    Article findById(Long id);

    void save(Article article);

    List<Article> findAll();

    void deleteById(Long id);
}
