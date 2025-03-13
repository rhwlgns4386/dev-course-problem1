package org.example.article.domain.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleTest {

    @Test
    void 아티클_생성() {
        String title = "첫과제 화이팅";
        String content = "모두 처음 과제 잘 해봅시다!";
        Article article = new Article(title, content);

        assertThat(article.title()).isEqualTo(title);
        assertThat(article.content()).isEqualTo(content);
    }

    @Test
    void 아티클_수정() {
        Article article = new Article("첫과제 화이팅", "모두 처음 과제 잘 해봅시다!");
        String title = "함께 성장";
        String content = "잘 모르면 서로 도와주기~";

        article.update(title, content);

        assertThat(article.title()).isEqualTo(title);
        assertThat(article.content()).isEqualTo(content);
    }
}