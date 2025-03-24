package org.example.auth.persentation.command;

import org.example.auth.config.AuthConfig;
import org.example.dispatcher.RequestHandler;
import org.example.global.exception.ExceptionHandler;

public class AccountRequestHandlerFactory {

    public static RequestHandler create() {
        return new AccountRequestHandlerAdaptor(AuthConfig.accountController(), new ExceptionHandler());
    }
}
