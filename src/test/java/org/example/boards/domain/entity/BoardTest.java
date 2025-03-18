package org.example.boards.domain.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void 보드생성(){
        String title = "제목";

        Board board = new Board(title);

        assertThat(board.title()).isEqualTo(title);
    }
}