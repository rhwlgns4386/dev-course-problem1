package org.example.article.cli.controller;

import org.example.article.cli.exception.WriteException;
import org.example.article.cli.view.ErrorView;

public class ExceptionHandler {

    public void handeWriteException(WriteException writeException){
        writeException.printStackTrace();
        ErrorView.renderError(writeException.getMessage());
    }

    public void handleRuntimeException(RuntimeException runtimeException){
        ErrorView.renderError("예기치 못한 상황이 발생하였습니다.");
    }
}
