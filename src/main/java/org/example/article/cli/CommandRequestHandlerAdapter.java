package org.example.article.cli;

import org.example.dispatcher.RequestHandler;
import org.example.article.cli.command.Command;
import org.example.article.cli.controller.CommandController;
import org.example.article.cli.controller.ExceptionHandler;
import org.example.article.cli.exception.WriteException;

public class CommandRequestHandlerAdapter implements RequestHandler {

    private final CommandController commandController;
    private final ExceptionHandler exceptionHandler;

    public CommandRequestHandlerAdapter(CommandController commandController, ExceptionHandler exceptionLogHandler) {
        this.commandController = commandController;
        this.exceptionHandler = exceptionLogHandler;
    }

    @Override
    public boolean support(String request) {
        return Command.findByName(request).isPresent();
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void run(String commandInput) {
        try {
            Command command = Command.findByName(commandInput).get();
            switch (command) {
                case EXIT -> commandController.exit();
                case WRITE -> commandController.write();
                case ALL ->  commandController.all();
            }
        }catch (Exception e) {
            handleException(e);
        }
    }

    private void handleException(Exception e) {
        if(e instanceof WriteException){
            exceptionHandler.handeWriteException((WriteException) e);
        }else if(e instanceof RuntimeException){
            exceptionHandler.handleRuntimeException((RuntimeException) e);
        }
        throw new RuntimeException(e);
    }
}
