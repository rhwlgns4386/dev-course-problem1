package org.example.boards.presentation;

import org.example.boards.presentation.controller.BoardController;
import org.example.dispatcher.dto.Request;
import org.example.global.exception.InvalidParamException;
import org.example.validator.NumberValidator;

import java.util.Arrays;
import java.util.Optional;

public enum BoardCommand {
    ADD("/add") {
        @Override
        public void execute(BoardController boardController, Request uriRequest) {
            String title = boardController.readBoardName();
            boardController.write(title);
        }
    }, EDIT("/edit") {
        @Override
        public void execute(BoardController boardController, Request uriRequest) {
            String boardId = uriRequest.getParameter("boardId");
            long id = toValidLong(boardId);
            boardController.containId(id);
            String title = boardController.readBoardName();
            boardController.edit(id, title);
        }
    }, REMOVE("/remove") {
        @Override
        public void execute(BoardController boardController, Request uriRequest) {
            String boardId = uriRequest.getParameter("boardId");
            long id =toValidLong(boardId);
            boardController.remove(id);
        }
    }, VIEW("/view") {
        @Override
        public void execute(BoardController boardController, Request uriRequest) {
            String boardId = uriRequest.getParameter("boardName");
            long id = toValidLong(boardId);
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

    public abstract void execute(BoardController boardController, Request uriRequest);

    private static InvalidParamException createInvalidParamException() {
        return new InvalidParamException("파라미터가 잘못 되었습니다.");
    }

    private static long toValidLong(String id) {
        if(id == null){
            throw createInvalidParamException();
        }
        NumberValidator.validate(id);
        return Long.parseLong(id);
    }
}
