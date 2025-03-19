package org.example.boards.presentation.command;

import org.example.boards.presentation.command.flow.AddFlow;
import org.example.boards.presentation.command.flow.EditFlow;
import org.example.boards.presentation.command.flow.RemoveFlow;
import org.example.boards.presentation.command.flow.ViewFlow;
import org.example.boards.presentation.controller.BoardController;
import org.example.cli.CommandFlow;
import org.example.cli.CommandFlowFinder;
import org.example.dispatcher.dto.Request;
import org.example.global.BaseRequestHandler;
import org.example.global.exception.CommandNotFoundException;
import org.example.global.exception.ExceptionHandler;

import java.util.List;
import java.util.Optional;

public class BoardRequestHandlerAdaptor extends BaseRequestHandler {

    private final String PREFIX = "/boards";
    private final BoardController boardController;
    private final CommandFlowFinder<BoardCommand,BoardController> finder;

    public BoardRequestHandlerAdaptor(BoardController boardController, ExceptionHandler exceptionHandler) {
        super(exceptionHandler);
        this.boardController = boardController;
        this.finder = new CommandFlowFinder<>(List.of(new AddFlow(), new EditFlow(), new RemoveFlow(),new ViewFlow()));
    }

    @Override
    public boolean support(Request request) {
        String command = request.getUrl();
        return command.startsWith(PREFIX);
    }

    @Override
    public void execute(Request commandInput) {
        BoardCommand command = BoardCommand.findPath(extractCommandString(commandInput)).orElseThrow(()->new CommandNotFoundException("존재하지 않는 명령어 입니다."));
        Optional<CommandFlow<BoardCommand, BoardController>> flow = finder.find(command);
        flow.ifPresent(commandFlow -> commandFlow.execute(boardController, commandInput));
    }

    private String extractCommandString(Request commandInput) {
        String url = commandInput.getUrl();
        return url.substring(PREFIX.length());
    }
}
