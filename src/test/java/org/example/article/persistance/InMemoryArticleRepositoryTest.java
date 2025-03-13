package org.example.article.persistance;

import org.example.article.domain.entity.Article;
import org.example.article.domain.service.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryArticleRepositoryTest {

    private ArticleRepository articleRepository;

    @BeforeEach
    void setUp() {
        articleRepository = new InMemoryArticleRepository();
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
        Article article = new Article("title", "content");

        articleRepository.save(article);

        assertThat(article.id()).isEqualTo(1L);
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void extractById() {
    }
}