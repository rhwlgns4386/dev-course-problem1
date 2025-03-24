package org.example.cli.runner;

import org.example.cli.runner.view.CliView;
import org.example.cli.view.ErrorView;
import org.example.dispatcher.*;
import org.example.dispatcher.dto.Request;
import org.example.dispatcher.exception.FormatException;

public class CliRunner implements ApplicationRunner {

    private final Dispather dispatchController;

    public CliRunner(OrderFilterConfig filterConfig, DispatchController dispatchController) {
        this.dispatchController = new FilterDispatcher(filterConfig, dispatchController);
    }

    public void run() {
        Long sessionId = null;
        while (ApplicationStateHolder.isRun()) {
            try {
                Request request = new Request(CliView.readCommand(),sessionId);
                dispatchController.dispatch(request);
                if(request.hasSession()){
                    sessionId = request.getSessionId();
                }else{
                    sessionId = null;
                }
            } catch (FormatException e) {
                ErrorView.renderError(e.getMessage());
            }
        }
    }
}
