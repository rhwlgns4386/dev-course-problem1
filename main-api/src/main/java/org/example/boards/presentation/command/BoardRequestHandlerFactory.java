package org.example.boards.presentation.command;

import org.example.boards.config.BoardConfig;
import org.example.dispatcher.RequestHandler;
import org.example.global.exception.ExceptionHandler;

public class BoardRequestHandlerFactory {

    public static RequestHandler create(){
        return new BoardRequestHandlerAdaptor(BoardConfig.boardController(),new ExceptionHandler());
    }
}
