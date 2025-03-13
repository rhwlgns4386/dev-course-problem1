package org.example.article.domain.service;

import org.example.article.domain.entity.Article;
import org.example.article.domain.entity.Articles;
import org.example.article.domain.exeption.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArticleServiceTest {

    private ArticleRepository articleRepository;
    private ArticleService articleService;

    @BeforeEach
    void initRepository() {
        articleRepository = new TestArticleRepository();
        articleRepository.save( new Article(1L, "title", "content"));

        articleService = new ArticleService(articleRepository);
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
    void 없는_게시글_조회시_예외() {
        Long id = 2L;

        assertThatThrownBy(() -> articleService.loadArticle(id))
                .isInstanceOf(EntityNotFoundException.class);
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

        Article article = articleRepository.findById(1L).get();
        assertThat(article.id()).isEqualTo(id);
        assertThat(article.title()).isEqualTo(title);
        assertThat(article.content()).isEqualTo(content);
    }

    @Test
    void  없는_게시글정수시_예외() {
        Long id = 2L;
        String title = "수정";
        String content = "수정되었습니다.";

        assertThatThrownBy(() -> articleService.update(id, title, content))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void 게시글삭제() {
        Long id = 2L;
        articleRepository.save(new Article(id, "title", "content"));

        articleService.delete(id);

        Optional<Article> article = articleRepository.findById(id);
        assertThat(article).isEmpty();
    }

    @Test
    void 없는_게시글삭제시_예외() {
        Long id = 2L;

        assertThatThrownBy(()->articleService.delete(id))
                .isInstanceOf(EntityNotFoundException.class);
    }

    private static final class TestArticleRepository implements ArticleRepository {
        private List<Article> articles = new ArrayList<>();

        @Override
        public Optional<Article> findById(Long id) {
            for (Article article : articles) {
                if(article.id().equals(id)) {
                    return Optional.of(article);
                }
            }
            return Optional.empty();
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
            findById(id).ifPresent(articles::remove);
        }

        @Override
        public boolean extractById(Long id) {
            for (Article article : articles) {
                if(article.id().equals(id)) {
                    return true;
                }
            }
            return false;
        }
    }
}