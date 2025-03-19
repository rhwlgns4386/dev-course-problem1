package org.example.dispatcher.dto;

import org.example.global.exception.FormatException;

import java.util.HashMap;
import java.util.Map;

public class Url {
    private final Map<String, String> parameters;
    private final String url;
    private final String path;

    public Url(String url) {
        validatePattern(url);
        String[] split = split(url);
        this.path = split[0];
        this.url = url;
        if(split.length >= 2){
            this.parameters = Url.initParams(split[1]);
        }else{
            this.parameters = new HashMap<>();
        }
    }

    private static String[] split(String url) {
        return url.split("\\?");
    }

    private static void validatePattern(String url) {
        if(!url.startsWith("/")){
            throw new FormatException("url패턴이 아닙니다.");
        }
    }

    static Map<String, String> initParams(String param) {
        Map<String, String> reusult = new HashMap<>();

        String[] querys = param.split("&");
        for (String query : querys) {
            String[] keyValues = query.split("=");
            reusult.put(keyValues[0], keyValues[1]);
        }
        return reusult;
    }

    public String getParmeter(String key) {
        return parameters.get(key);
    }

    public String url() {
        return url;
    }

    public String path() {
        return path;
    }
}