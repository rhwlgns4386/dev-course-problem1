package org.example.article.presentation;

import org.example.article.presentation.exception.CommandNotFoundException;
import org.example.global.exception.PresentationException;
import org.example.dispatcher.RequestHandler;
import org.example.article.presentation.command.Command;
import org.example.article.presentation.controller.CommandController;
import org.example.global.exception.ExceptionHandler;
import org.example.dispatcher.dto.Request;

public class CommandRequestHandlerAdapter implements RequestHandler {

    private static final String SUPPORT_TYPE = "command";

    private final CommandController commandController;
    private final ExceptionHandler exceptionHandler;

    public CommandRequestHandlerAdapter(CommandController commandController, ExceptionHandler exceptionLogHandler) {
        this.commandController = commandController;
        this.exceptionHandler = exceptionLogHandler;
    }

    @Override
    public boolean support(Request request) {
        return request.type().equals(SUPPORT_TYPE);
    }

    @Override
    public void run(Request request) {
        try {
            Command command = Command.findByName(request.command()).orElseThrow(()->new CommandNotFoundException("존재하지 않는 명령어 입니다."));
            command.execute(commandController);
        }catch (Exception e) {
            handleException(e);
        }
    }

    private void handleException(Exception e) {
        if(e instanceof PresentationException){
            exceptionHandler.handlePresentationException((PresentationException) e);
            return;
        } else if(e instanceof RuntimeException){
            exceptionHandler.handleRuntimeException((RuntimeException) e);
            return;
        }
        throw new RuntimeException(e);
    }
}
