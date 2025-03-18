package org.example.article.presentation;

import org.example.global.exception.ExceptionHandler;
import org.example.dispatcher.RequestHandler;
import org.example.article.presentation.config.CommandConfig;
import org.example.article.presentation.config.DefaultCommandConfig;

public class CommandRequestHandlerFactory {
    private static CommandConfig commandConfig = new DefaultCommandConfig();

    public static void init(CommandConfig commandConfig) {
        CommandRequestHandlerFactory.commandConfig = commandConfig;
    }

    public static RequestHandler create(){
        return new CommandRequestHandlerAdapter(commandConfig.commandController(),new ExceptionHandler());
    }
}
