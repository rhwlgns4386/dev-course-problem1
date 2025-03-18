package org.example.article.presentation;

import org.example.global.BaseRequestHandler;
import org.example.global.exception.CommandNotFoundException;
import org.example.global.exception.PresentationException;
import org.example.dispatcher.RequestHandler;
import org.example.article.presentation.command.Command;
import org.example.article.presentation.controller.CommandController;
import org.example.global.exception.ExceptionHandler;
import org.example.dispatcher.dto.Request;

public class CommandRequestHandlerAdapter extends BaseRequestHandler {

    private static final String SUPPORT_TYPE = "command";

    private final CommandController commandController;

    public CommandRequestHandlerAdapter(CommandController commandController, ExceptionHandler exceptionLogHandler) {
        super(exceptionLogHandler);
        this.commandController = commandController;
    }

    @Override
    public boolean support(Request request) {
        return request.type().equals(SUPPORT_TYPE);
    }


    @Override
    public void execute(Request request) {
        Command command = Command.findByName(request.command()).orElseThrow(()->new CommandNotFoundException("존재하지 않는 명령어 입니다."));
        command.execute(commandController);
    }
}
