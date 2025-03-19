package org.example.cli.runner;

public class CountBaseApplicationState implements ApplicationState{

    private int count;

    public CountBaseApplicationState(int count) {
        this.count = count;
    }

    @Override
    public boolean isRun() {
        return 0 < count--;
    }

    @Override
    public void stop() {
        count = 0;
    }
}
