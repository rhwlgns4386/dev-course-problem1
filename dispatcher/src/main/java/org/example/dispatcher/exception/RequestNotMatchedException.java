package org.example.dispatcher.exception;

public class RequestNotMatchedException extends RuntimeException {
    public RequestNotMatchedException(String message) {
        super(message);
    }
}
