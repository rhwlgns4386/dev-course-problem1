package org.example.article.presentation.exception;

import org.example.global.exception.PresentationException;

public class ArticleNotFoundException extends PresentationException {
    public ArticleNotFoundException(String message) {
        super(message);
    }

    public ArticleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
