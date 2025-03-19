package org.example.cli.runner;

public final class ApplicationStateHolder {
    private static  ApplicationState applicationState = new BooleanApplicationState();

    private ApplicationStateHolder() {
    }

    public static void setApplicationState(ApplicationState applicationState) {
        ApplicationStateHolder.applicationState = applicationState;
    }

    public static boolean isRun(){
        return applicationState.isRun();
    }

    public static void stop(){
        applicationState.stop();
    }
}
