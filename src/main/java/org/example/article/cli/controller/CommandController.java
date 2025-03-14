package org.example.article.cli.controller;

import org.example.article.cli.ApplicationStateHolder;
import org.example.article.cli.view.OutputView;

public class CommandController {

    public void exit(){
        ApplicationStateHolder.stop();
        OutputView.renderExit();
    }
}
