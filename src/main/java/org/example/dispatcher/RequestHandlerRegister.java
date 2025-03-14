package org.example.dispatcher;

import java.util.ArrayList;
import java.util.List;

public final class RequestHandlerRegister {

    private static final List<RequestHandler> handlers = new ArrayList<>();
    private RequestHandlerRegister() {
    }

    public static void register(RequestHandler handler) {
        handlers.add(handler);
    }

    static HandlerMapper toHandlerMapper() {
        return new HandlerMapper(handlers);
    }
}
