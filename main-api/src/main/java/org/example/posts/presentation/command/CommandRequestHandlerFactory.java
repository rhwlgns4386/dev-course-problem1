package org.example.posts.presentation.command;

import org.example.dispatcher.RequestHandler;
import org.example.global.exception.ExceptionHandler;
import org.example.posts.config.DefaultPostsConfig;
import org.example.posts.config.PostsConfig;

public class CommandRequestHandlerFactory {
    private static PostsConfig postsConfig = new DefaultPostsConfig();

    public static void init(PostsConfig postsConfig) {
        CommandRequestHandlerFactory.postsConfig = postsConfig;
    }

    public static RequestHandler create(){
        return new PostRequestHandlerAdaptor(postsConfig.commandController(),new ExceptionHandler());
    }
}
