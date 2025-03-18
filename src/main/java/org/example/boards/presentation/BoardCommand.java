package org.example.boards.presentation;

import org.example.boards.presentation.controller.BoardController;
import org.example.dispatcher.dto.UriRequest;
import org.example.global.exception.CommandNotFoundException;
import org.example.global.exception.InvalidParamException;

import java.util.Arrays;
import java.util.Optional;

public enum BoardCommand {
    ADD("/add") {
        @Override
        public void execute(BoardController boardController, UriRequest uriRequest) {
            String title = boardController.readBoardName();
            boardController.write(title);
        }
    }, EDIT("/edit") {
        @Override
        public void execute(BoardController boardController, UriRequest uriRequest) {
            String boardId = uriRequest.getParameter("boardId");
            if (boardId == null) {
                throw createInvalidParamException();
            }
            long id = Long.parseLong(boardId);
            boardController.containId(id);
            String title = boardController.readBoardName();
            boardController.edit(id, title);
        }
    }, REMOVE("/remove") {
        @Override
        public void execute(BoardController boardController, UriRequest uriRequest) {
            String boardId = uriRequest.getParameter("boardId");
            if (boardId == null) {
                throw createInvalidParamException();
            }
            long id = Long.parseLong(boardId);
            boardController.remove(id);
        }
    }, VIEW("/view") {
        @Override
        public void execute(BoardController boardController, UriRequest uriRequest) {
            String boardId = uriRequest.getParameter("boardName");
            if (boardId == null) {
               throw createInvalidParamException();
            }
            long id = Long.parseLong(boardId);
            boardController.load(id);
        }
    };

    private String path;

    BoardCommand(String path){
        this.path = path;
    }
    public static Optional<BoardCommand> findPath(String path){
        return Arrays.stream(values()).filter((value)->path.contains(value.path)).findFirst();
    }

    public abstract void execute(BoardController boardController, UriRequest uriRequest);

    private static InvalidParamException createInvalidParamException() {
        return new InvalidParamException("파라미터가 잘못 되었습니다.");
    }
}
