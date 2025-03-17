package org.example.article.cli.runner;

import org.example.article.cli.view.CliView;
import org.example.dispatcher.ApplicationRunner;
import org.example.dispatcher.DispatchController;
import org.example.article.cli.view.InputView;
import org.example.dispatcher.dto.Request;

public class CliRunner implements ApplicationRunner {

    private final DispatchController dispatchController;

    public CliRunner(DispatchController dispatchController) {
        this.dispatchController = dispatchController;
    }

    public void run(){
        while (ApplicationStateHolder.isRun()) {
            dispatchController.dispatch(new Request("command", CliView.readCommand()));
        }
    }
}
