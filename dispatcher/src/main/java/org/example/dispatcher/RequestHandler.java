package org.example.dispatcher;

import org.example.dispatcher.dto.Request;

public interface RequestHandler {
    boolean support(Request request);

    void run(Request commandInput);
}
