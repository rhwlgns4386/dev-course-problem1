package org.example.global.exception;

public class CommandNotFoundException extends PresentationException {
    public CommandNotFoundException(String message) {
        super(message);
    }
}
