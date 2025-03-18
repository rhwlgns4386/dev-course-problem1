package org.example.dispatcher.dto;

import java.util.HashMap;
import java.util.Map;

public class UriRequest extends Request {
    private static final Map<String,String> parameters = new HashMap<>();
    public UriRequest(String type, String command) {
        super(type, command);
        init(command);
    }

    private static void init(String command){
        String[] split = command.split("\\?");
        if(split.length != 2){
            return;
        }
        String[] querys = split[1].split("&");
        for (String query : querys) {
            String[] keyValues = query.split("=");
            parameters.put(keyValues[0], keyValues[1]);
        }
    }

    public UriRequest(String command) {
        this("url",command);
    }

    public String getParameter(String key){
        return parameters.get(key);
    }

}
