package org.example.dispatcher.dto;

public class Request {
    private final String type;
    private final String command;

    public Request(String type, String command) {
        this.type = type;
        this.command = command;
    }

    public String type() {
        return type;
    }

    public String command() {
        return command;
    }
}
