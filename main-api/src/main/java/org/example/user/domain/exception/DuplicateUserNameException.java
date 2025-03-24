package org.example.user.domain.exception;

import org.example.global.exception.BusinessException;

public class DuplicateUserNameException extends BusinessException {
    public DuplicateUserNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUserNameException(String message) {
        super(message);
    }
}
