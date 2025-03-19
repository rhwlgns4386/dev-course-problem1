package org.example.global.exception;

public class InvalidParamException extends FormatException {
    public InvalidParamException(String message) {
        super(message);
    }
}
