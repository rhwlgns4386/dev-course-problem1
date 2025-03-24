package org.example.posts.presentation.exception;

import org.example.global.exception.PresentationException;

public class PostNotFoundException extends PresentationException {
    public PostNotFoundException(String message) {
        super(message);
    }

    public PostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
