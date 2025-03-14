package org.example.article.cli.controller;

import org.example.article.cli.dto.request.WriteDto;
import org.example.article.cli.exception.WriteException;
import org.example.article.cli.runner.ApplicationStateHolder;
import org.example.article.cli.view.InputView;
import org.example.article.cli.view.OutputView;
import org.example.article.domain.exeption.EntityCreationException;
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

    public void write(){
        WriteDto writeDto = InputView.readWriteDto();
        try {
            articleService.save(writeDto.title(), writeDto.content());
        }catch (EntityCreationException e){
            throw new WriteException("게시글은 제목과 내용이 필수 입니다.",e);
        }
    }
}
