package org.example.cli.runner;

public class BooleanApplicationState implements ApplicationState {

    private boolean run = true;

    @Override
    public boolean isRun() {
        return run;
    }

    @Override
    public void stop() {
        run = false;
    }
}
