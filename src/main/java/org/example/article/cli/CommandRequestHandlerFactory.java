package org.example.article.cli;

import org.example.dispatcher.RequestHandler;
import org.example.article.cli.config.CommandConfig;
import org.example.article.cli.config.DefaultCommandConfig;
import org.example.article.cli.controller.ExceptionLogHandler;

public class CommandRequestHandlerFactory {
    private static CommandConfig commandConfig = new DefaultCommandConfig();

    public static void init(CommandConfig commandConfig) {
        CommandRequestHandlerFactory.commandConfig = commandConfig;
    }

    public static RequestHandler create(){
        return new CommandRequestHandlerAdapter(commandConfig.commandController(),new ExceptionLogHandler());
    }
}
