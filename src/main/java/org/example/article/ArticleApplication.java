package org.example.article;

import org.example.article.cli.ApplicationStateHolder;
import org.example.article.cli.DispatchController;
import org.example.article.cli.config.CommandConfig;
import org.example.article.cli.config.DefaultCommandConfig;
import org.example.article.cli.controller.CommandController;
import org.example.article.cli.view.InputView;

public class ArticleApplication {

    private static CommandConfig commandConfig = new DefaultCommandConfig();

    public static void init(CommandConfig commandConfig){
        ArticleApplication.commandConfig = commandConfig;
    }

    public static void main(String[] args) {

        DispatchController dispatchController = new DispatchController(commandConfig.commandController());

        while (ApplicationStateHolder.isRun()) {
            dispatchController.dispatch(InputView.readCommand());
        }
    }
}
