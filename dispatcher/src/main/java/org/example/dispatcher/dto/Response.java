package org.example.dispatcher.dto;

import org.example.dispatcher.session.SessionHolder;

public class Response {

    private Long sessionId;

    public Response(Long sessionId) {
        this.sessionId = sessionId;
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

    public void logout() {
        SessionHolder.removeSession(this.sessionId);
        this.sessionId = null;
    }
}
