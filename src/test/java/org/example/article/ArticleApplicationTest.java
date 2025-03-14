package org.example.article;

import org.example.ApplicationTest;
import org.example.article.cli.ApplicationStateHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.assertj.core.api.Assertions.assertThat;

public class ArticleApplicationTest extends ApplicationTest {

    @Override
    @BeforeEach
    protected void init() {
        ApplicationStateHolder.start();
        super.init();
    }

    @ParameterizedTest
    @ValueSource(strings = {"종료","exit","EXIT"})
    void 종료_명령어를_입력시_종료된다(String command) {
        run(()->{
            in(command);
            ArticleApplication.main(new String[]{});
        });
        assertThat(out()).contains("프로그램이 종료됩니다.");
    }
}
