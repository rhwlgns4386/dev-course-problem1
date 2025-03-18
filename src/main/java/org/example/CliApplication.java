package org.example;

import org.example.article.presentation.CommandRequestHandlerFactory;
import org.example.boards.presentation.BoardRequestHandlerFactory;
import org.example.cli.runner.CliRunner;
import org.example.dispatcher.ApplicationRunner;
import org.example.dispatcher.DispatchController;
import org.example.dispatcher.HandlerMapper;
import org.example.dispatcher.HandlerMapperFactory;

public class CliApplication {

    public static void main(String[] args) {
        HandlerMapper handlerMapper = HandlerMapperFactory.handlerMapper(
                CommandRequestHandlerFactory.create(),
                BoardRequestHandlerFactory.create()
        );
        run(handlerMapper);
    }

    private static void run(HandlerMapper handlerMapper) {
        DispatchController dispatchController = new DispatchController(handlerMapper);
        ApplicationRunner applicationRunner = new CliRunner(dispatchController);
        applicationRunner.run();
    }
}
