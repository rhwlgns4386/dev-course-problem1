package org.example.cli.runner;

import org.example.cli.runner.view.CliView;
import org.example.dispatcher.ApplicationRunner;
import org.example.dispatcher.DispatchController;
import org.example.dispatcher.dto.Request;
import org.example.global.exception.FormatException;
import org.example.global.ErrorView;

public class CliRunner implements ApplicationRunner {

    private final DispatchController dispatchController;

    public CliRunner(DispatchController dispatchController) {
        this.dispatchController = dispatchController;
    }

    public void run(){
        while (ApplicationStateHolder.isRun()) {
            try {
                dispatchController.dispatch(new Request(CliView.readCommand()));
            }catch (FormatException e){
                ErrorView.renderError(e.getMessage());
            }
        }
    }
}
