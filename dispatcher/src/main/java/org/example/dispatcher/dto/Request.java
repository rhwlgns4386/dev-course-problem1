package org.example.dispatcher.dto;

import org.example.dispatcher.session.Session;
import org.example.dispatcher.session.SessionHolder;

public class Request {
    private final Url url;

    public Request(String url) {
        this.url = new Url(url);
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
