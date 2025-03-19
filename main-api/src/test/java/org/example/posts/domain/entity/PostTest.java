package org.example.posts.domain.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.example.posts.TestBoards.board;

class PostTest {



    @Test
    void 아티클_생성() {
        String title = "첫과제 화이팅";
        String content = "모두 처음 과제 잘 해봅시다!";
        Post post = new Post(title, content,board);

        assertThat(post.title()).isEqualTo(title);
        assertThat(post.content()).isEqualTo(content);
    }

    @Test
    void 아티클_수정() {
        Post post = new Post("첫과제 화이팅", "모두 처음 과제 잘 해봅시다!",board);
        String title = "함께 성장";
        String content = "잘 모르면 서로 도와주기~";

        post.update(title, content);

        assertThat(post.title()).isEqualTo(title);
        assertThat(post.content()).isEqualTo(content);
    }

    @ParameterizedTest
    @CsvSource(value = {"title,",",content"},delimiter = ',')
    void 제목혹은_컨텐츠가_비어있다면_예외(String title, String content) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Post(title, content,board));
    }

    @Test
    void 게시판을_포함하지_않으면_예외() {
        String title = "첫과제 화이팅";
        String content = "모두 처음 과제 잘 해봅시다!";
        assertThatIllegalArgumentException().isThrownBy(() -> new Post(title, content,null));
    }
}