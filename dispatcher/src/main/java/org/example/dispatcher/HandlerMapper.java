package org.example.dispatcher;

import org.example.dispatcher.dto.Request;
import org.example.dispatcher.exception.RequestNotMatchedException;

import java.util.List;

public class HandlerMapper {
    private final List<RequestHandler> handlers;

    public HandlerMapper(List<RequestHandler> handlers) {
        this.handlers = handlers;
    }

    public RequestHandler findRequestHandler(Request request) {
        for (RequestHandler handler : handlers) {
            if(handler.support(request)){
                return handler;
            }
        }
        throw new RequestNotMatchedException("요청을 찾을 수 없습니다");
    }
}
