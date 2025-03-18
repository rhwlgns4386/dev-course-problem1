package org.example.dispatcher.dto;

import org.example.article.presentation.command.Command;

import java.util.Optional;

public class Request {
    private final String type;
    private final String command;

    public Request(String type, String command) {
        this.type = type;
        this.command = command;
    }

    public Request(String command) {
        this(getType(command),command);
    }

    private static String getType(String command){
        Optional<Command> byName = Command.findByName(command);
        if(byName.isPresent()){
            return "command";
        }
        return "url";
    }

    public String type() {
        return type;
    }

    public String command() {
        return command;
    }
}
