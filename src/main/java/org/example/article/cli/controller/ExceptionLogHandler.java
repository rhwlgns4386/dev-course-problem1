package org.example.article.cli.controller;

import org.example.article.cli.exception.CommandNotFoundException;
import org.example.article.cli.exception.WriteException;

public class ExceptionLogHandler extends ExceptionHandler{

    @Override
    public void handeWriteException(WriteException e) {
        e.printStackTrace();
        super.handeWriteException(e);
    }

    @Override
    public void handleCommandNotFoundException(CommandNotFoundException e) {
        e.printStackTrace();
        super.handleCommandNotFoundException(e);
    }

    @Override
    public void handleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        super.handleRuntimeException(e);
    }
}
