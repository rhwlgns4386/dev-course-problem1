package org.example.posts.presentation;

import org.example.global.exception.ExceptionHandler;
import org.example.dispatcher.RequestHandler;
import org.example.posts.presentation.config.PostsConfig;
import org.example.posts.presentation.config.DefaultPostsConfig;

public class CommandRequestHandlerFactory {
    private static PostsConfig postsConfig = new DefaultPostsConfig();

    public static void init(PostsConfig postsConfig) {
        CommandRequestHandlerFactory.postsConfig = postsConfig;
    }

    public static RequestHandler create(){
        return new CommandRequestHandlerAdapter(postsConfig.commandController(),new ExceptionHandler());
    }
}
