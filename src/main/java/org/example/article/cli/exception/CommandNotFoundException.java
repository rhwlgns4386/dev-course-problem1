package org.example.article.cli.exception;

public class CommandNotFoundException extends PresentationException {
    public CommandNotFoundException(String message) {
        super(message);
    }
}
