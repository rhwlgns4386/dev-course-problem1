package org.example.boards.domain.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.NullString;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void 보드생성(){
        String title = "제목";

        Board board = new Board(title);

        assertThat(board.title()).isEqualTo(title);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ","   "})
    void 제목없이_제목_생성시_예외(String title){
        assertThatIllegalArgumentException().isThrownBy(() -> new Board(title));
    }
}