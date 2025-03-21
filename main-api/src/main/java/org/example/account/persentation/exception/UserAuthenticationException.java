package org.example.account.persentation.exception;

import org.example.global.exception.PresentationException;

public class UserAuthenticationException extends PresentationException {
    public UserAuthenticationException(String message) {
        super(message);
    }

    public UserAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
