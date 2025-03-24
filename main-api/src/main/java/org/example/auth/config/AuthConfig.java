package org.example.auth.config;

import org.example.auth.persentation.controller.AccountController;
import org.example.auth.domain.service.AccountReader;
import org.example.auth.reader.InMemoryAccountReader;
import org.example.user.config.UserConfig;

public class AuthConfig {

    private static AccountReader reader;


    public static AccountController accountController(){
        return new AccountController(accountRepository());
    }

    public static AccountReader accountRepository(){
        if(reader == null){
            reader = new InMemoryAccountReader(UserConfig.userRepository());
        }
        return reader;
    }

}
