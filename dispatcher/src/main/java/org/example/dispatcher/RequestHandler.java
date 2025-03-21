package org.example.dispatcher;

import org.example.dispatcher.dto.Request;
import org.example.dispatcher.dto.Response;

public interface RequestHandler {
    boolean support(Request request);

    void run(Request request, Response response);
}
