package org.example.article;

import org.example.ApplicationTest;
import org.example.article.cli.runner.ApplicationStateHolder;
import org.example.article.cli.config.DefaultCommandConfig;
import org.example.article.domain.entity.Article;
import org.example.article.domain.service.ArticleRepository;
import org.example.article.persistance.InMemoryArticleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.assertj.core.api.Assertions.assertThat;

public class ArticleApplicationTest extends ApplicationTest {

    private final static ArticleRepository articleRepository = new InMemoryArticleRepository();

    @BeforeAll
    public static void initConfig(){
        ArticleApplication.init(new TestConfig());
    }

    @Override
    @BeforeEach
    protected void init() {
        articleRepository.clear();
        ApplicationStateHolder.start();
        super.init();
    }

    @ParameterizedTest
    @ValueSource(strings = {"종료", "exit", "EXIT"})
    void 종료_명령어를_입력시_종료된다(String command) {
        run(() -> {
            in(command);
            ArticleApplication.main(new String[]{});
        });
        assertThat(out()).contains("프로그램이 종료됩니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"목록","all","ALL"})
    void 목록_조회(String command) {
        Article article1 = new Article("제목1", "내용1");
        Article article2 = new Article("제목2", "내용2");
        articleRepository.save(article1);
        articleRepository.save(article2);

        run(() -> {
            in(command,"종료");
            ArticleApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "총 게시글은 2개 작성되어있습니다."
                ,"1번 게시글"
                , "제목 : 제목1"
                , "내용 : 내용1"
                , "2번 게시글"
                , "제목 : 제목2"
                , "내용 : 내용2"
        );
    }

    private static class TestConfig extends DefaultCommandConfig {
        @Override
        public ArticleRepository articleRepository() {
            return articleRepository;
        }
    }
}
