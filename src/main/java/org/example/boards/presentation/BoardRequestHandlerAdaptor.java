package org.example.boards.presentation;

import org.example.article.presentation.dto.request.IdDto;
import org.example.boards.presentation.controller.BoardController;
import org.example.dispatcher.RequestHandler;
import org.example.dispatcher.dto.Request;
import org.example.dispatcher.dto.UriRequest;

import java.util.regex.Pattern;

public class BoardRequestHandlerAdaptor implements RequestHandler {

    private final String PREFIX = "/boards";
    private final BoardController boardController;

    public BoardRequestHandlerAdaptor(BoardController boardController) {
        this.boardController = boardController;
    }

    @Override
    public boolean support(Request request) {
        if (!(request instanceof UriRequest)) {
            return false;
        }
        String command = request.command();
        return command.startsWith(PREFIX);
    }

    @Override
    public void run(Request commandInput) {
        UriRequest uriRequest = (UriRequest) commandInput;
        String value = commandInput.command();
        String command = value.substring(PREFIX.length());
        if (command.startsWith("/add")) {
            String title = boardController.readBoardName();
            boardController.write(title);
        } else if (command.startsWith("/edit")) {
            String boardId = uriRequest.getParameter("boardId");
            if (boardId == null) {
                return;
            }
            long id = Long.parseLong(boardId);
            boardController.containId(id);
            String title = boardController.readBoardName();
            boardController.edit(id, title);
        }
    }
}
