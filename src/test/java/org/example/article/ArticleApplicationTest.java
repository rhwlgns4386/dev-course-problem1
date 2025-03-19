package org.example.article;

import org.example.ApplicationTest;
import org.example.CliApplication;
import org.example.CliApplicationTest;
import org.example.article.presentation.CommandRequestHandlerFactory;
import org.example.cli.runner.ApplicationStateHolder;
import org.example.article.presentation.config.DefaultCommandConfig;
import org.example.article.domain.entity.Article;
import org.example.article.domain.service.ArticleRepository;
import org.example.article.persistance.InMemoryArticleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.assertj.core.api.Assertions.assertThat;

public class ArticleApplicationTest extends CliApplicationTest {

    private final static ArticleRepository articleRepository = new InMemoryArticleRepository();

    @BeforeAll
    public static void initConfig() {
        CommandRequestHandlerFactory.init(new TestConfig());
    }


    @Override
    @BeforeEach
    protected void init() {
        articleRepository.clear();
        super.init();
    }

    @ParameterizedTest
    @ValueSource(strings = {"종료", "exit", "EXIT"})
    void 종료_명령어를_입력시_종료된다(String command) {
        run(() -> {
            in(input->input.command(command));
            CliApplication.main(new String[]{});
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
            in(input->input.command(command));
            CliApplication.main(new String[]{});
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
            in(input->input.command(command).input(title,content).command("목록"));
            CliApplication.main(new String[]{});
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
            in(input->input.command("수정").input("",""));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "게시글은 제목과 내용이 필수 입니다."
        );
    }

    @Test
    void 게시글_작성_예외2() {
        Article article1 = new Article("제목1", "내용1");
        articleRepository.save(article1);

        run(() -> {
            in(input->input.command("수정").input("1번"," "," "));
            CliApplication.main(new String[]{});
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
            in(input->input.command("조회").input("1번").command("조회").input("2번"));
            CliApplication.main(new String[]{});
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
            in((input)->input.command("조회").input("2번"));
            CliApplication.main(new String[]{});
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
            in((input)->input.command(command).input("1번"));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "어떤 게시물을 삭제할까요?",
                "1번 게시물이 성공적으로 삭제되었습니다!"
        );
    }

    @Test
    void 없는_게시글_삭제_시_예외() {
        Article article1 = new Article("제목1", "내용1");
        articleRepository.save(article1);

        run(() -> {
            in(inputBuilder -> inputBuilder.command("삭제").input("2번"));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "2번 게시글은 존재하지 않습니다"
        );
    }


    @ParameterizedTest
    @ValueSource(strings = {"수정","update","UPDATE"})
    void 게시글_수정(String command) {
        Article article1 = new Article("제목1", "내용1");
        articleRepository.save(article1);

        run(() -> {
            in((input)->input.command(command).input("1번","제목2","내용1"));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "어떤 게시물을 수정할까요?",
                "1번 게시물을 수정합니다.",
                "제목 : ",
                "내용 : ",
                "1번 게시물이 성공적으로 수정되었습니다!"
        );
    }

    @Test
    void 없는_게시글_수정() {

        run(() -> {
            in((input -> input.command("수정").input("1번")));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "어떤 게시물을 수정할까요?",
                "1번 게시글은 존재하지 않습니다"
        );
    }

    private static class TestConfig extends DefaultCommandConfig {
        @Override
        public ArticleRepository articleRepository() {
            return articleRepository;
        }
    }
}
