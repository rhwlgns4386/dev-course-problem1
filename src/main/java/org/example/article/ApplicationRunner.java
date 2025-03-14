package org.example.article;

import org.example.article.cli.ApplicationStateHolder;
import org.example.article.cli.DispatchController;
import org.example.article.cli.view.InputView;

public class ApplicationRunner {

    private final DispatchController dispatchController;

    public ApplicationRunner(DispatchController dispatchController) {
        this.dispatchController = dispatchController;
    }

    public void run(){
        while (ApplicationStateHolder.isRun()) {
            dispatchController.dispatch(InputView.readCommand());
        }
    }
}
