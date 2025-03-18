package org.example.article.presentation.controller;

import org.example.article.presentation.dto.request.IdDto;
import org.example.article.presentation.dto.request.ArticleInfoDto;
import org.example.article.presentation.exception.ArticleNotFoundException;
import org.example.article.presentation.exception.WriteException;
import org.example.cli.runner.ApplicationStateHolder;
import org.example.article.presentation.view.InputView;
import org.example.article.presentation.view.OutputView;
import org.example.article.domain.entity.Article;
import org.example.article.domain.exeption.EntityCreationException;
import org.example.article.domain.exeption.EntityNotFoundException;
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

    public ArticleInfoDto readInfo(){
        return InputView.readArticleInfo();
    }

    public void write(ArticleInfoDto articleInfoDto){
        try {
            articleService.save(articleInfoDto.title(), articleInfoDto.content());
        }catch (EntityCreationException e){
            throw new WriteException("게시글은 제목과 내용이 필수 입니다.",e);
        }
    }

    public String readLookUpId(){
        return InputView.readId("조회");
    }

    public void lookup(IdDto id){
        try {
            Article article = articleService.loadArticle(id.toLong());
            OutputView.render(article);
        }catch (EntityNotFoundException e){
            articleNotFoundException(id, e);
        }
    }

    public String readDeleteId(){
        return InputView.readId("삭제");
    }

    public void delete(IdDto id) {
        try {
            articleService.delete(id.toLong());
            OutputView.renderDeleted(id.toLong());
        }catch (EntityNotFoundException e){
            articleNotFoundException(id, e);
        }
    }

    public String readUpdateId(){
        return InputView.readId("수정");
    }

    public ArticleInfoDto readUpdateInfo(IdDto id){
        return InputView.readUpdateInfo(id.toLong());
    }

    public void containId(IdDto id) {
        try {
            articleService.validateContainsId(id.toLong());
        }catch (EntityNotFoundException e){
            articleNotFoundException(id, e);
        }
    }

    public void update(IdDto id, ArticleInfoDto article) {
        try {
            articleService.update(id.toLong(), article.title(), article.content());
            OutputView.renderUpdate(id.toLong());
        }catch (EntityNotFoundException e){
            articleNotFoundException(id, e);
        }
    }

    private static void articleNotFoundException(IdDto id, EntityNotFoundException e) {
        throw new ArticleNotFoundException(String.format("%s번 게시글은 존재하지 않습니다.", id.toLong()), e);
    }

}
