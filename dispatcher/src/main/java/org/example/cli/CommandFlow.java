package org.example.cli;

import org.example.dispatcher.dto.Request;
import org.example.dispatcher.dto.Response;

public abstract class CommandFlow<K> {
    private final K key;

    public CommandFlow(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }


    public abstract void execute(Request request);
}
