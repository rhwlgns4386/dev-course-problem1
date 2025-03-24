package org.example.dispatcher.dto;

import org.example.dispatcher.session.Session;
import org.example.dispatcher.session.SessionHolder;

public class Request {
    private final Url url;
    private Long sessionId;

    public Request(String url,Long sessionId) {
        this.url = new Url(url);
        this.sessionId = sessionId;
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

    public void setSession() {
        this.sessionId = SessionHolder.createSession();
    }

    public boolean hasSession() {
        return sessionId != null;
    }

    public Long getSessionId() {
        return this.sessionId;
    }

    public Session getSession() {
        return SessionHolder.getSession(this.sessionId);
    }

    public void logout() {
        SessionHolder.removeSession(this.sessionId);
        this.sessionId = null;
    }

}
