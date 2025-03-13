package org.example.article.domain.service;

import org.example.article.domain.entity.Article;
import org.example.article.domain.entity.Articles;
import org.example.article.domain.exeption.EntityNotFoundException;

public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article loadArticle(Long id) {
        return findByIdOrElseThrow(id);
    }

    public void update(Long id, String title, String content) {
        Article article = findByIdOrElseThrow(id);
        article.update(title, content);
    }

    private Article findByIdOrElseThrow(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Article not found"));
    }

    public Articles loadAll() {
        return new Articles(articleRepository.findAll());
    }

    public void delete(Long id) {
        if (!articleRepository.extractById(id)) throw new EntityNotFoundException("Article not found");
        articleRepository.deleteById(id);
    }
}
