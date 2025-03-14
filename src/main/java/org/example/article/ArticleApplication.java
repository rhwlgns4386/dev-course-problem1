package org.example.article;

import org.example.article.cli.ApplicationStateHolder;
import org.example.article.cli.DispatchController;
import org.example.article.cli.command.Command;
import org.example.article.cli.controller.CommandController;
import org.example.article.cli.view.InputView;
import org.example.article.cli.view.OutputView;

import java.util.Optional;

public class ArticleApplication {
    public static void main(String[] args) {

        DispatchController dispatchController = new DispatchController(new CommandController());

        while (ApplicationStateHolder.isRun()) {
            dispatchController.dispatch(InputView.readCommand());
        }
    }
}
