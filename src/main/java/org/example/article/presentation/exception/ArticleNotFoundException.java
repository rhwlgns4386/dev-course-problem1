package org.example.article.presentation.exception;

public class ArticleNotFoundException extends PresentationException{
    public ArticleNotFoundException(String message) {
        super(message);
    }

    public ArticleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
