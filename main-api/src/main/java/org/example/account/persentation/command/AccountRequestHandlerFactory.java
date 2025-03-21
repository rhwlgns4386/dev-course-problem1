package org.example.account.persentation.command;

import org.example.account.config.AccountConfig;
import org.example.dispatcher.RequestHandler;
import org.example.global.exception.ExceptionHandler;

public class AccountRequestHandlerFactory {

    public static RequestHandler create() {
        return new AccountRequestHandlerAdaptor(AccountConfig.userController(), AccountConfig.accountController(), new ExceptionHandler());
    }
}
