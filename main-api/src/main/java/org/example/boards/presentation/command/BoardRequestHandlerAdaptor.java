package org.example.boards.presentation.command;

import org.example.boards.presentation.command.flow.AddFlow;
import org.example.boards.presentation.command.flow.EditFlow;
import org.example.boards.presentation.command.flow.RemoveFlow;
import org.example.boards.presentation.command.flow.ViewFlow;
import org.example.boards.presentation.controller.BoardController;
import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;
import org.example.global.BaseRequestHandler;
import org.example.global.exception.CommandNotFoundException;
import org.example.global.exception.ExceptionHandler;

import java.util.List;

public class BoardRequestHandlerAdaptor extends BaseRequestHandler<BoardCommand> {

    private final String PREFIX = "/boards";

    public BoardRequestHandlerAdaptor(BoardController boardController, ExceptionHandler exceptionHandler) {
        super(exceptionHandler,commandFlows(boardController));
    }

    private static List<CommandFlow<BoardCommand>> commandFlows(BoardController boardController) {
        return List.of(new AddFlow(boardController), new EditFlow(boardController), new RemoveFlow(boardController), new ViewFlow(boardController));
    }

    @Override
    public boolean support(Request request) {
        String command = request.getUrl();
        return command.startsWith(PREFIX);
    }

    private String extractCommandString(Request commandInput) {
        String url = commandInput.getUrl();
        return url.substring(PREFIX.length());
    }

    @Override
    public BoardCommand extractCommandKey(Request request) {
        return BoardCommand.findPath(extractCommandString(request)).orElseThrow(() -> new CommandNotFoundException("존재하지 않는 명령어 입니다."));
    }
}
