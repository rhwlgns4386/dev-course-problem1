package org.example.boards.presentation;

import org.example.boards.config.BoardConfig;
import org.example.dispatcher.RequestHandler;

public class BoardRequestHandlerFactory {
    private static BoardConfig config = new BoardConfig();

    public static void init(BoardConfig config) {
        BoardRequestHandlerFactory.config = config;
    }

    public static RequestHandler create(){
        return new BoardRequestHandlerAdaptor(config.boardController());
    }
}
