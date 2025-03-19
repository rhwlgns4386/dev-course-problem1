package org.example.posts.presentation;

import org.example.dispatcher.RequestHandler;
import org.example.global.exception.ExceptionHandler;
import org.example.posts.presentation.config.DefaultPostsConfig;
import org.example.posts.presentation.config.PostsConfig;

public class CommandRequestHandlerFactory {
    private static PostsConfig postsConfig = new DefaultPostsConfig();

    public static void init(PostsConfig postsConfig) {
        CommandRequestHandlerFactory.postsConfig = postsConfig;
    }

    public static RequestHandler create(){
        return new PostRequestHandlerAdaptor(postsConfig.commandController(),new ExceptionHandler());
    }
}
