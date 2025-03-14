package org.example.dispatcher;

public interface RequestHandler {
    boolean support(String request);

    void run(String commandInput);
}
