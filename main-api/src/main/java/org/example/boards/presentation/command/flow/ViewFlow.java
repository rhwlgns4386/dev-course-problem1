package org.example.boards.presentation.command.flow;

import org.example.boards.presentation.command.BoardCommand;
import org.example.boards.presentation.controller.BoardController;
import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;
import org.example.dispatcher.dto.Response;
import org.example.global.ExceptionBoxCommandFlow;
import org.example.global.exception.InvalidParamException;
import org.example.validator.StringValidator;

import static org.example.boards.presentation.command.ValidationLongConverter.convert;

public class ViewFlow extends ExceptionBoxCommandFlow<BoardCommand> {

    private final  BoardController controller;

    public ViewFlow(BoardController controller) {
        super(BoardCommand.VIEW);
        this.controller = controller;
    }

    @Override
    public void doAfter(Request request) {
        String name = request.getParameter("boardName");
        StringValidator.validate(name);
        controller.load(name);
    }
}
