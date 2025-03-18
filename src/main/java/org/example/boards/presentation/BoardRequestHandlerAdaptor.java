package org.example.boards.presentation;

import org.example.boards.presentation.controller.BoardController;
import org.example.dispatcher.RequestHandler;
import org.example.dispatcher.dto.Request;

import java.util.regex.Pattern;

public class BoardRequestHandlerAdaptor implements RequestHandler {

    private final String PREFIX = "/boards";
    private final BoardController boardController;

    public BoardRequestHandlerAdaptor(BoardController boardController) {
        this.boardController = boardController;
    }

    @Override
    public boolean support(Request request) {
        String command = request.command();
        return command.startsWith(PREFIX);
    }

    @Override
    public void run(Request commandInput) {
        String value = commandInput.command();
        String command = value.substring(PREFIX.length());
        if(command.equals("/add")){
            String title = boardController.readBoardName();
            boardController.write(title);
        }
    }
}
