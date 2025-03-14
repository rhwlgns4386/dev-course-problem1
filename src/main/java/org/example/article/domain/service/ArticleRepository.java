package org.example.article.domain.service;

import org.example.article.domain.entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Optional<Article> findById(Long id);

    void save(Article article);

    List<Article> findAll();

    void deleteById(Long id);

    boolean extractById(Long id);

    void clear();
}
