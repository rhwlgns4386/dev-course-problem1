package org.example.article.cli.runner;

import org.example.article.cli.DispatchController;
import org.example.article.cli.view.InputView;

public class CliRunner implements org.example.article.ApplicationRunner {

    private final DispatchController dispatchController;

    public CliRunner(DispatchController dispatchController) {
        this.dispatchController = dispatchController;
    }

    public void run(){
        while (ApplicationStateHolder.isRun()) {
            dispatchController.dispatch(InputView.readCommand());
        }
    }
}
