package org.example.cli.view;

public final class ErrorView {
    private ErrorView() {

    }

    public static void renderError(String message){
        System.out.println(message);
    }
}
