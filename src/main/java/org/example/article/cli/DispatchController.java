package org.example.article.cli;

import org.example.article.cli.command.Command;
import org.example.article.cli.controller.CommandController;


public class DispatchController {

    private final CommandController commandController;

    public DispatchController(CommandController commandController) {
        this.commandController = commandController;
    }

    public void dispatch(String commandInput){
        Command command = Command.findByName(commandInput).orElseThrow();
        dispatch(command);
    }

    private void  dispatch(Command command){
        if(command.equals(Command.EXIT)){
            commandController.exit();
        }
        if(command.equals(Command.ALL)){
            commandController.all();
        }
        if(command.equals(Command.WRITE)){
            commandController.write();
        }
    }
}
