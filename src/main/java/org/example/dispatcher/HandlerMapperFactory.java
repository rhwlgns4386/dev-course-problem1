package org.example.dispatcher;

import java.util.List;

public final class HandlerMapperFactory {

    private HandlerMapperFactory() {
    }

    public static HandlerMapper handlerMapper(RequestHandler ...handlers) {
        return new HandlerMapper(List.of(handlers));
    }
}
