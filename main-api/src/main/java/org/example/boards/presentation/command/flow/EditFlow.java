package org.example.boards.presentation.command.flow;

import org.example.boards.presentation.command.BoardCommand;
import org.example.boards.presentation.command.ValidationLongConverter;
import org.example.boards.presentation.controller.BoardController;
import org.example.dispatcher.dto.Request;
import org.example.global.ExceptionBoxCommandFlow;

public class EditFlow extends ExceptionBoxCommandFlow<BoardCommand> {

    private final BoardController controller;

    public EditFlow(BoardController boardController) {
        super(BoardCommand.EDIT);
        this.controller = boardController;
    }

    @Override
    public void doAfter(Request request) {
        String boardId = request.getParameter("boardId");
        long id = ValidationLongConverter.convert(boardId);
        controller.containId(id);
        String title = controller.readBoardName();
        controller.edit(id, title);
    }
}
