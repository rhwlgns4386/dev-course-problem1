package org.example.article.presentation.exception;

public class CommandNotFoundException extends PresentationException {
    public CommandNotFoundException(String message) {
        super(message);
    }
}
