package org.example.article;

import org.example.ApplicationTest;
import org.example.dispatcher.ArticleApplication;
import org.example.article.cli.CommandRequestHandlerFactory;
import org.example.article.cli.runner.ApplicationStateHolder;
import org.example.article.cli.config.DefaultCommandConfig;
import org.example.article.domain.entity.Article;
import org.example.article.domain.service.ArticleRepository;
import org.example.article.persistance.InMemoryArticleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.assertj.core.api.Assertions.assertThat;

public class ArticleApplicationTest extends ApplicationTest {

    private final static ArticleRepository articleRepository = new InMemoryArticleRepository();

    @BeforeAll
    public static void initConfig() {
        CommandRequestHandlerFactory.init(new TestConfig());
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

    @ParameterizedTest
    @ValueSource(strings = {"작성","write","WRITE"})
    void 게시글_작성(String command) {
        String title = "제목1";
        String content = "내용1";

        run(() -> {
            in(command,title,content,"목록","종료");
            ArticleApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "총 게시글은 1개 작성되어있습니다."
                ,"1번 게시글"
                , "제목 : 제목1"
                , "내용 : 내용1"
        );
    }

    @Test
    void 게시글_작성_예외() {

        run(() -> {
            in("작성","","","종료");
            ArticleApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "게시글은 제목과 내용이 필수 입니다."
        );
    }

    @Test
    void 게시글_조회() {
        Article article1 = new Article("제목1", "내용1");
        Article article2 = new Article("제목2", "내용2");
        articleRepository.save(article1);
        articleRepository.save(article2);

        run(() -> {
            in("조회","1번","조회","2번","종료");
            ArticleApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "1번 게시글",
                "제목 : 제목1",
                "내용 : 내용1",
                "2번 게시글",
                "제목 : 제목2",
                "내용 : 내용2"
        );
    }

    @Test
    void 없는_게시글_조회_시_예외() {
        Article article1 = new Article("제목1", "내용1");
        articleRepository.save(article1);

        run(() -> {
            in("조회","2번","종료");
            ArticleApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "2번 게시글은 존재하지 않습니다"
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"삭제","delete","DELETE"})
    void 없는_게시글_삭제(String command) {
        Article article1 = new Article("제목1", "내용1");
        articleRepository.save(article1);

        run(() -> {
            in(command,"1번","종료");
            ArticleApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "어떤 게시물을 삭제할까요?",
                "1번 게시물이 성공적으로 삭제되었습니다!"
        );
    }

    private static class TestConfig extends DefaultCommandConfig {
        @Override
        public ArticleRepository articleRepository() {
            return articleRepository;
        }
    }
}
