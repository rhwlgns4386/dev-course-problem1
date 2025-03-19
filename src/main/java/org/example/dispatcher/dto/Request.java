package org.example.dispatcher.dto;

import org.example.posts.presentation.command.Command;

import java.util.Optional;

public class Request {
    private final String type;
    private final String command;

    public Request(String type, String command) {
        this.type = type;
        this.command = command;
    }

    public static Request of(String command){
        Optional<Command> byName = Command.findByName(command);
        if(byName.isPresent()){
            return new Request("command",command);
        }
        return new UriRequest(command);
    }

    public String type() {
        return type;
    }

    public String command() {
        return command;
    }
}
