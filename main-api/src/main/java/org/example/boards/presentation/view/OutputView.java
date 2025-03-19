package org.example.boards.presentation.view;

import org.example.boards.domain.entity.Board;

import java.time.format.DateTimeFormatter;

public final class OutputView {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void renderBoard(Board load) {
        System.out.printf("%s/%s/%s\n",load.id(),load.title(),load.updateAt().format(formatter));
    }
}
