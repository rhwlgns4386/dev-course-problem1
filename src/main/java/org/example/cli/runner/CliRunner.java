package org.example.cli.runner;

import org.example.cli.runner.view.CliView;
import org.example.dispatcher.ApplicationRunner;
import org.example.dispatcher.DispatchController;
import org.example.dispatcher.dto.Request;

public class CliRunner implements ApplicationRunner {

    private final DispatchController dispatchController;

    public CliRunner(DispatchController dispatchController) {
        this.dispatchController = dispatchController;
    }

    public void run(){
        while (ApplicationStateHolder.isRun()) {
            dispatchController.dispatch(Request.of(CliView.readCommand()));
        }
    }
}
