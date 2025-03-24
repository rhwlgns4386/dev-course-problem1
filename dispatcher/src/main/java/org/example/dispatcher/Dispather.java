package org.example.dispatcher;

import org.example.dispatcher.dto.Request;

public interface Dispather {
    void dispatch(Request request);
}
