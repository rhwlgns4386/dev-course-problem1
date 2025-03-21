package org.example.account.config;

import org.example.account.domain.service.UserRepository;
import org.example.account.domain.service.UserService;
import org.example.account.persentation.controller.AccountController;
import org.example.account.persentation.controller.AccountRepository;
import org.example.account.persentation.controller.UserController;
import org.example.account.persistance.InMemoryUserRepository;

public class AccountConfig {

    private static UserController userController;
    private static UserService userService;
    private static InMemoryUserRepository repository;

    public static UserController userController(){
        if(userController == null){
            userController = new UserController(userService());
        }
        return userController;
    }

    public static UserService userService(){
        if(userService == null){
            userService = new UserService(userRepository());
        }
        return userService;
    }

    public static UserRepository userRepository(){
        if(repository == null){
            repository = new InMemoryUserRepository();
        }
        return repository;
    }

    public static AccountController accountController(){
        return new AccountController(accountRepository());
    }

    public static AccountRepository accountRepository(){
        if(repository == null){
            repository = new InMemoryUserRepository();
        }
        return repository;
    }

}
