package org.example.article.domain.service;

import org.example.article.domain.entity.Article;
import org.example.article.domain.entity.Articles;

public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article loadArticle(Long id) {
        return articleRepository.findById(id);
    }

    public void update(Long id, String title, String content) {
        Article article = articleRepository.findById(id);
        article.update(title, content);
    }

    public Articles loadAll() {
        return new Articles(articleRepository.findAll());
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
        return ;
    }
}
