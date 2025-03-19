package org.example.boards.presentation.exception;

import org.example.global.exception.PresentationException;

public class BoardNotFoundException extends PresentationException {
    public BoardNotFoundException(String message) {
        super(message);
    }

    public BoardNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
