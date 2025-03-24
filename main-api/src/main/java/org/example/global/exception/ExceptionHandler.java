package org.example.global.exception;

import org.example.cli.view.ErrorView;

public class ExceptionHandler {

    public void handlePresentationException(PresentationException e){
        ErrorView.renderError(e.getMessage());
    }

    public void handleFormatException(Exception e) {
        ErrorView.renderError(e.getMessage());
    }

    public void handleRuntimeException(Exception runtimeException){
        ErrorView.renderError("예기치 못한 상황이 발생하였습니다.");
    }

    public void handleIllegaltException(IllegalArgumentException e) {
        System.out.println("잘못된 입력입니다.");
    }
}
