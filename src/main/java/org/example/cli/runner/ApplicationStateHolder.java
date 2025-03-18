package org.example.cli.runner;

public final class ApplicationStateHolder {
    private static boolean run = true;

    private ApplicationStateHolder() {
    }

    public static boolean isRun(){
        return run;
    }

    public static void start(){
        run = true;
    }

    public static void stop(){
        run = false;
    }
}
