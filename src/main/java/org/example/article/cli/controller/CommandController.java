package org.example.article.cli.controller;

import org.example.article.cli.runner.ApplicationStateHolder;
import org.example.article.cli.view.OutputView;
import org.example.article.domain.service.ArticleService;

public class CommandController {

    private final ArticleService articleService;

    public CommandController(ArticleService articleService) {
        this.articleService = articleService;
    }

    public void exit(){
        ApplicationStateHolder.stop();
        OutputView.renderExit();
    }

    public void all(){
        OutputView.renderList(articleService.loadAll());
    }
}
