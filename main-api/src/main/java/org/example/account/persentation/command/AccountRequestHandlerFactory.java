package org.example.account.persentation.command;

import org.example.account.persentation.config.AccountConfig;
import org.example.dispatcher.RequestHandler;
import org.example.global.exception.ExceptionHandler;

public class AccountRequestHandlerFactory {

    public static RequestHandler create(){
        return new AccountRequestHandlerAdaptor(AccountConfig.userController(),new ExceptionHandler());
    }
}
