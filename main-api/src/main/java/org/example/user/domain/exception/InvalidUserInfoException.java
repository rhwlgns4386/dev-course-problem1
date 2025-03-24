package org.example.user.domain.exception;

import org.example.global.exception.BusinessException;

public class InvalidUserInfoException extends BusinessException {
    public InvalidUserInfoException(String message) {
        super(message);
    }

    public InvalidUserInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}
