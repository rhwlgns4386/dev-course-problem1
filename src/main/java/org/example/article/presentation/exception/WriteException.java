package org.example.article.presentation.exception;

import org.example.global.exception.PresentationException;

public class WriteException extends PresentationException {
    public WriteException(String message) {
        super(message);
    }

    public WriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
