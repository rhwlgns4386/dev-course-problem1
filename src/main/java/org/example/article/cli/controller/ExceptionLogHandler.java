package org.example.article.cli.controller;

import org.example.article.cli.exception.CommandNotFoundException;
import org.example.article.cli.exception.PresentationException;
import org.example.article.cli.exception.WriteException;

public class ExceptionLogHandler extends ExceptionHandler{

    @Override
    public void handlePresentationException(PresentationException e) {
        e.printStackTrace();
        super.handlePresentationException(e);
    }

    @Override
    public void handleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        super.handleRuntimeException(e);
    }
}
