package org.example.dispatcher.dto;

import java.util.HashMap;
import java.util.Map;

public class UriRequest extends Request {
    private final Map<String,String> parameters;
    public UriRequest(String type, String command) {
        super(type, command);
        this.parameters = initParams(command);
    }

    private static Map<String,String> initParams(String command){
        Map<String,String> reusult = new HashMap<>();
        String[] split = command.split("\\?");
        if(split.length != 2){
            return new HashMap<>();
        }
        String[] querys = split[1].split("&");
        for (String query : querys) {
            String[] keyValues = query.split("=");
            reusult.put(keyValues[0], keyValues[1]);
        }
        return reusult;
    }

    public UriRequest(String command) {
        this("url",command);
    }

    public String getParameter(String key){
        return parameters.get(key);
    }

}
