package org.example.account.persentation.config;

import org.example.account.domain.service.UserRepository;
import org.example.account.domain.service.UserService;
import org.example.account.persentation.controller.UserController;
import org.example.account.persistance.InMemoryUserRepository;

public class AccountConfig {

    private static UserController userController;
    private static UserService userService;
    private static UserRepository userRepository;

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
        if(userRepository == null){
            userRepository = new InMemoryUserRepository();
        }
        return userRepository;
    }

    public static void setBoardRepository(UserRepository userRepository){
        AccountConfig.userRepository = userRepository;
    }
}
