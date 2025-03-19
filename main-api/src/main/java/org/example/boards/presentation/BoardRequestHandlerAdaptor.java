package org.example.boards.presentation;

import org.example.boards.presentation.controller.BoardController;
import org.example.dispatcher.dto.Request;
import org.example.global.BaseRequestHandler;
import org.example.global.exception.CommandNotFoundException;
import org.example.global.exception.ExceptionHandler;

public class BoardRequestHandlerAdaptor extends BaseRequestHandler {

    private final String PREFIX = "/boards";
    private final BoardController boardController;

    public BoardRequestHandlerAdaptor(BoardController boardController, ExceptionHandler exceptionHandler) {
        super(exceptionHandler);
        this.boardController = boardController;
    }

    @Override
    public boolean support(Request request) {
        String command = request.getUrl();
        return command.startsWith(PREFIX);
    }

    @Override
    public void execute(Request commandInput) {
        BoardCommand command = BoardCommand.findPath(extractCommandString(commandInput)).orElseThrow(()->new CommandNotFoundException("존재하지 않는 명령어 입니다."));
        command.execute(boardController, commandInput);
    }

    private String extractCommandString(Request commandInput) {
        String url = commandInput.getUrl();
        return url.substring(PREFIX.length());
    }
}
