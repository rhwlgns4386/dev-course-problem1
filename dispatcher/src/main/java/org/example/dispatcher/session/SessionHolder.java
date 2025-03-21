package org.example.dispatcher.session;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public final class SessionHolder {

    private static final Map<Long, Session> sessions = new HashMap<>();
    private static Long key = 0L;

    public static Session getSession(Long id) {
        if(id == null || !sessions.containsKey(id)) {
            return null;
        }
        return sessions.get(id);
    }

    public static Long createSession() {
        Long key = SessionHolder.key++;
        sessions.put(key,new Session(key));
        return key;
    }

    public static void removeSession(Long id) {
        sessions.remove(id);
    }
}
