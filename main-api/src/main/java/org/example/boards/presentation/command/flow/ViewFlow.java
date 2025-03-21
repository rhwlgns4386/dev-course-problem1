package org.example.boards.presentation.command.flow;

import org.example.boards.presentation.command.BoardCommand;
import org.example.boards.presentation.controller.BoardController;
import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;
import org.example.dispatcher.dto.Response;
import org.example.global.exception.InvalidParamException;

import static org.example.boards.presentation.command.ValidationLongConverter.convert;

public class ViewFlow extends CommandFlow<BoardCommand> {

    private final  BoardController controller;

    public ViewFlow(BoardController controller) {
        super(BoardCommand.VIEW);
        this.controller = controller;
    }

    @Override
    public void execute(Request request, Response response) {
        String boardId = request.getParameter("boardName");
        try {
            long id = convert(boardId);
            controller.load(id);
        }catch (NullPointerException e){
            throw new InvalidParamException("파라미터가 잘못 되었습니다.");
        }
    }
}
