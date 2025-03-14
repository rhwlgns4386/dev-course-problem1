package org.example.article;

import org.example.article.cli.DispatchController;
import org.example.article.cli.config.CommandConfig;
import org.example.article.cli.config.DefaultCommandConfig;
import org.example.article.cli.controller.ExceptionHandler;
import org.example.article.cli.controller.ExceptionLogHandler;
import org.example.article.cli.runner.CliRunner;

public class ArticleApplication {

    private static CommandConfig commandConfig = new DefaultCommandConfig();

    public static void init(CommandConfig commandConfig){
        ArticleApplication.commandConfig = commandConfig;
    }

    public static void main(String[] args) {
        DispatchController dispatchController = new DispatchController(commandConfig.commandController(),new ExceptionLogHandler());
        ApplicationRunner applicationRunner = new CliRunner(dispatchController);
        applicationRunner.run();
    }
}
