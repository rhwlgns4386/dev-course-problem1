package org.example.dispatcher.session;

import java.util.HashMap;
import java.util.Map;

public class Session {

    private final Long sessionId;
    private final Map<String,Object> itmes;

    public Session(Long sessionId) {
        this.sessionId = sessionId;
        this.itmes = new HashMap<>();
    }

    public void add(String key, Object value) {
        itmes.put(key,value);
    }

    public <T> T get(String key,Class<T> clazz) {
        return clazz.cast(itmes.get(key));
    }

    public void remove(String key) {
        itmes.remove(key);
    }
}
