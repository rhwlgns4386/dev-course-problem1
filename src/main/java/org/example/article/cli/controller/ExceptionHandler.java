package org.example.article.cli.controller;

import org.example.article.cli.exception.PresentationException;
import org.example.article.cli.view.ErrorView;

public class ExceptionHandler {

    public void handlePresentationException(PresentationException e){
        ErrorView.renderError(e.getMessage());
    }

    public void handleRuntimeException(RuntimeException runtimeException){
        ErrorView.renderError("예기치 못한 상황이 발생하였습니다.");
    }
}
