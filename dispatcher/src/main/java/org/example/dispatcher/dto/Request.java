package org.example.dispatcher.dto;

import org.example.dispatcher.session.Session;
import org.example.dispatcher.session.SessionHolder;

public class Request {
    private final Url url;
    private final Session session;

    public Request(Long sessionId, String url) {
        this.url = new Url(url);
        session = SessionHolder.getSession(sessionId);
    }

    public String getParameter(String key) {
        return url.getParmeter(key);
    }

    public String getUrl() {
        return url.url();
    }

    public String getPath() {
        return url.path();
    }
}
