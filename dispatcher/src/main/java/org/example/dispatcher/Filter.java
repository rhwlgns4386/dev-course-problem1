package org.example.dispatcher;

import org.example.dispatcher.dto.Request;

public interface Filter {

    void doFilter(Filter filter, Request request);
}
