package org.example.article.cli;

import org.example.article.cli.exception.CommandNotFoundException;
import org.example.article.cli.exception.PresentationException;
import org.example.dispatcher.RequestHandler;
import org.example.article.cli.command.Command;
import org.example.article.cli.controller.CommandController;
import org.example.article.cli.controller.ExceptionHandler;
import org.example.article.cli.exception.WriteException;
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
            switch (command) {
                case EXIT -> commandController.exit();
                case WRITE -> commandController.write();
                case ALL ->  commandController.all();
                case LOOKUP -> commandController.lookup();
            }
        }catch (Exception e) {
            handleException(e);
        }
    }

    private void handleException(Exception e) {
        if(e instanceof PresentationException){
            exceptionHandler.handlePresentationException((PresentationException) e);
        } else if(e instanceof RuntimeException){
            exceptionHandler.handleRuntimeException((RuntimeException) e);
        }
        throw new RuntimeException(e);
    }
}
