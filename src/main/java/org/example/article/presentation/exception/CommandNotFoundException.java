package org.example.article.presentation.exception;

import org.example.global.exception.PresentationException;

public class CommandNotFoundException extends PresentationException {
    public CommandNotFoundException(String message) {
        super(message);
    }
}
