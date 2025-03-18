package org.example.global.exception;

import org.example.article.presentation.view.ErrorView;

public class ExceptionHandler {

    public void handlePresentationException(PresentationException e){
        ErrorView.renderError(e.getMessage());
    }

    public void handleRuntimeException(RuntimeException runtimeException){
        ErrorView.renderError("예기치 못한 상황이 발생하였습니다.");
    }
}
