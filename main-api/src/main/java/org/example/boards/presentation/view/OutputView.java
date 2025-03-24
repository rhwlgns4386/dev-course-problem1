package org.example.boards.presentation.view;

import org.example.boards.domain.entity.Board;

import java.time.format.DateTimeFormatter;
import java.util.List;

public final class OutputView {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void renderBoard(Board load) {
        System.out.printf("%s/%s/%s\n",load.id(),load.title(),load.updateAt().format(formatter));
    }

    public static void renderSuccessDelete() {
        System.out.println("삭제에 성공하였습니다.");
    }

    public static void renderBoardAll(List<Board> load) {
        for (Board board : load) {
            renderBoard(board);
        }
    }
}
