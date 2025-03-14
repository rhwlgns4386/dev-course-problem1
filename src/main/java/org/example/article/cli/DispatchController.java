package org.example.article.cli;

import org.example.article.cli.command.Command;
import org.example.article.cli.controller.CommandController;
import org.example.article.cli.controller.ExceptionHandler;
import org.example.article.cli.exception.WriteException;


public class DispatchController {

    private final CommandController commandController;
    private final ExceptionHandler exceptionHandler;

    public DispatchController(CommandController commandController,ExceptionHandler exceptionHandler) {
        this.commandController = commandController;
        this.exceptionHandler = exceptionHandler;
    }

    public void dispatch(String commandInput){
        Command command = Command.findByName(commandInput).orElseThrow();
        try{
            dispatch(command);
        }catch (Exception e){
            handleException(e);
        }
    }

    private void handleException(Exception e) {
        if(e instanceof WriteException){
            exceptionHandler.handeWriteException((WriteException) e);
        }else if(e instanceof RuntimeException){
            exceptionHandler.handleRuntimeException((RuntimeException) e);
        }
    }

    private void  dispatch(Command command){
        if(command.equals(Command.EXIT)){
            commandController.exit();
        } else if(command.equals(Command.ALL)){
            commandController.all();
        }else if(command.equals(Command.WRITE)){
            commandController.write();
        }
    }
}
