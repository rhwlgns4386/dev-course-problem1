package org.example.posts.presentation.view;

public final class ErrorView {
    private ErrorView() {

    }

    public static void renderError(String message){
        System.out.println(message);
    }
}
