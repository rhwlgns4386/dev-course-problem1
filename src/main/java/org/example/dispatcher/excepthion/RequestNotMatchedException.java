package org.example.dispatcher.excepthion;

public class RequestNotMatchedException extends RuntimeException {
    public RequestNotMatchedException(String message) {
        super(message);
    }
}
