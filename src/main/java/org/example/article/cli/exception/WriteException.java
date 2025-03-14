package org.example.article.cli.exception;

public class WriteException extends PresentationException {
    public WriteException(String message) {
        super(message);
    }

    public WriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
