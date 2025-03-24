package org.example.boards.domain.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class BoardTest {

    @Test
    void 보드생성() {
        String title = "제목";

        Board board = new Board(new Title(title));

        assertThat(board.title()).isEqualTo(title);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "   "})
    void 제목없이_제목_생성시_예외(String title) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Board(new Title(title)));
    }

    @Test
    void 보드수정() {
        Board board = new Board(new Title("title"));
        Title title = new Title("제목");

        board.update(title);

        assertThat(board.title()).isEqualTo(title.toStringValue());
        assertThat(board.updateAt()).isEqualTo(title.createAt());
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "   "})
    void 제목없이_제목으로_수정시_예외(String title) {
        Board board = new Board(new Title("title"));
        assertThatIllegalArgumentException().isThrownBy(() -> board.update(new Title(title)));
    }
}