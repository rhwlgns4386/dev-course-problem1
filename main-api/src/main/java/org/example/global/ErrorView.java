package org.example.global;

public final class ErrorView {
    private ErrorView() {

    }

    public static void renderError(String message){
        System.out.println(message);
    }
}
