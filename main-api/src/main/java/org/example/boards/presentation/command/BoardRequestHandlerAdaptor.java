package org.example.boards.presentation.command;

import org.example.boards.presentation.command.flow.AddFlow;
import org.example.boards.presentation.command.flow.EditFlow;
import org.example.boards.presentation.command.flow.RemoveFlow;
import org.example.boards.presentation.command.flow.ViewFlow;
import org.example.boards.presentation.controller.BoardController;
import org.example.cli.CommandFlow;
import org.example.cli.CommandFlowFinder;
import org.example.dispatcher.dto.Request;
import org.example.dispatcher.dto.Response;
import org.example.global.BaseRequestHandler;
import org.example.global.exception.CommandNotFoundException;
import org.example.global.exception.ExceptionHandler;

import java.util.List;
import java.util.Optional;

public class BoardRequestHandlerAdaptor extends BaseRequestHandler {

    private final String PREFIX = "/boards";
    private final CommandFlowFinder<BoardCommand> finder;

    public BoardRequestHandlerAdaptor(BoardController boardController, ExceptionHandler exceptionHandler) {
        super(exceptionHandler);
        this.finder = new CommandFlowFinder<>(List.of(new AddFlow(boardController), new EditFlow(boardController), new RemoveFlow(boardController), new ViewFlow(boardController)));
    }

    @Override
    public boolean support(Request request) {
        String command = request.getUrl();
        return command.startsWith(PREFIX);
    }

    @Override
    public void execute(Request request) throws Exception {
        BoardCommand command = BoardCommand.findPath(extractCommandString(request)).orElseThrow(() -> new CommandNotFoundException("존재하지 않는 명령어 입니다."));
        Optional<CommandFlow<BoardCommand>> flow = finder.find(command);
        flow.ifPresent(commandFlow -> commandFlow.execute(request));
    }

    private String extractCommandString(Request commandInput) {
        String url = commandInput.getUrl();
        return url.substring(PREFIX.length());
    }
}
