package org.example.dispatcher;

import org.example.article.presentation.CommandRequestHandlerFactory;
import org.example.cli.runner.CliRunner;

public class ArticleApplication {

    static {
        RequestHandlerRegister.register(CommandRequestHandlerFactory.create());
    }

    public static void main(String[] args) {
        DispatchController dispatchController = new DispatchController(RequestHandlerRegister.toHandlerMapper());
        ApplicationRunner applicationRunner = new CliRunner(dispatchController);
        applicationRunner.run();
    }
}
