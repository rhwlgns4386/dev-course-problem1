package org.example.user.persentation.command;

import org.example.dispatcher.RequestHandler;
import org.example.global.exception.ExceptionHandler;
import org.example.user.config.UserConfig;

public class UserRequestHandlerFactory {

    public static RequestHandler create() {
        return new UserRequestHandlerAdaptor(UserConfig.userController(), new ExceptionHandler());
    }
}
