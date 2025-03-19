package org.example.cli;

import org.example.dispatcher.dto.Request;

public abstract class CommandFlow<K,C> {
    private final K key;

    public CommandFlow(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }


    public abstract void execute(C controller, Request request);
}
