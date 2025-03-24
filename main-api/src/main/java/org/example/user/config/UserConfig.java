package org.example.user.config;

import org.example.user.domain.service.UserRepository;
import org.example.user.domain.service.UserService;
import org.example.user.persentation.controller.UserController;
import org.example.user.persistance.InMemoryUserRepository;

public class UserConfig {

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

}
