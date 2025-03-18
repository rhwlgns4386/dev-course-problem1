package org.example.article.presentation.view;

public final class ErrorView {
    private ErrorView() {

    }

    public static void renderError(String message){
        System.out.println(message);
    }
}
