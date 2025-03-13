package org.example.article.domain.service;

import org.example.article.domain.entity.Article;
import org.example.article.domain.entity.Articles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleServiceTest {

    private final ArticleRepository articleRepository = new TestArticleRepository();
    private final ArticleService articleService = new ArticleService(articleRepository);

    @BeforeEach
    void initRepository() {
        Article before = new Article(1L, "title", "content");
        articleRepository.save(before);
    }

    @Test
    void 저장된_게시글조회() {
        Long id = 1L;

        Article article = articleService.loadArticle(id);

        String title = "title";
        String content = "content";
        assertThat(article.id()).isEqualTo(id);
        assertThat(article.content()).isEqualTo(content);
        assertThat(article.title()).isEqualTo(title);
    }

    @Test
    void 게시글_목록조회() {
        articleRepository.save(new Article(2L, "title", "content"));

        Articles articles = articleService.loadAll();

        assertThat(articles.size()).isEqualTo(2);
        assertThat(articles).isEqualTo(new Articles(articleRepository.findAll()));
    }

    @Test
    void 게시글수정() {
        Long id = 1L;
        String title = "수정";
        String content = "수정되었습니다.";

        articleService.update(id, title, content);

        Article article = articleRepository.findById(1L);
        assertThat(article.id()).isEqualTo(id);
        assertThat(article.title()).isEqualTo(title);
        assertThat(article.content()).isEqualTo(content);
    }

    @Test
    void 게시글_목록삭제() {
        Long id = 2L;
        articleRepository.save(new Article(id, "title", "content"));

        articleService.delete(id);

        Article article = articleRepository.findById(id);
        assertThat(article).isNull();
    }

    private static final class TestArticleRepository implements ArticleRepository {
        private List<Article> articles = new ArrayList<>();

        @Override
        public Article findById(Long id) {
            for (Article article : articles) {
                if(article.id().equals(id)) {
                    return article;
                }
            }
            return null;
        }

        @Override
        public void save(Article article) {
           articles.add(article);
        }

        @Override
        public List<Article> findAll() {
            return articles;
        }

        @Override
        public void deleteById(Long id) {
            articles.remove(findById(id));
        }
    }
}