package org.example.article.persistance;

import org.example.article.domain.entity.Article;
import org.example.article.domain.service.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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
        Article article = new Article("title", "content");

        articleRepository.save(article);

        Optional<Article> result = articleRepository.findById(1L);
        assertThat(result).isPresent();
        result.ifPresent((value)->{
            assertThat(value.title()).isEqualTo(article.title());
            assertThat(value.content()).isEqualTo(article.content());
        });
    }

    @Test
    void save() {
        Article article = new Article("title", "content");

        articleRepository.save(article);

        assertThat(article.id()).isEqualTo(1L);
    }

    @Test
    void findAll() {
        Article article = new Article("title", "content");
        Article article2 = new Article("title2", "content2");

        articleRepository.save(article);
        articleRepository.save(article2);

        List<Article> all = articleRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        assertThat(all).contains(article,article2);
    }

    @Test
    void deleteById() {
        Article article = new Article("title", "content");

        articleRepository.save(article);

        articleRepository.deleteById(1L);

        assertThat(articleRepository.findById(1L)).isEmpty();
    }

    @Test
    void extractById() {
        Article article = new Article("title", "content");

        articleRepository.save(article);

        assertThat(articleRepository.extractById(1L)).isTrue();
        assertThat(articleRepository.extractById(2L)).isFalse();
    }
}