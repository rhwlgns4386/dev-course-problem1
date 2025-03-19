package org.example.posts.presentation.controller;

import org.example.posts.presentation.dto.request.IdDto;
import org.example.posts.presentation.dto.request.PostInfoDto;
import org.example.posts.presentation.exception.PostNotFoundException;
import org.example.posts.presentation.exception.WriteException;
import org.example.cli.runner.ApplicationStateHolder;
import org.example.posts.presentation.view.InputView;
import org.example.posts.presentation.view.OutputView;
import org.example.posts.domain.entity.Post;
import org.example.posts.domain.exeption.EntityCreationException;
import org.example.posts.domain.exeption.EntityNotFoundException;
import org.example.posts.domain.service.PostsService;
import org.example.global.exception.PresentationException;

public class PostsController {

    private final PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    public void exit(){
        ApplicationStateHolder.stop();
        OutputView.renderExit();
    }

    public void all(){
        OutputView.renderList(postsService.loadAll());
    }

    public PostInfoDto readInfo(){
        return InputView.readArticleInfo();
    }

    public void write(PostInfoDto postInfoDto){
        try {
            postsService.save(postInfoDto.title(), postInfoDto.content());
        }catch (EntityCreationException e){
            throw new WriteException("게시글은 제목과 내용이 필수 입니다.",e);
        }
    }

    public String readLookUpId(){
        return InputView.readId("조회");
    }

    public void lookup(IdDto id){
        try {
            Post post = postsService.loadArticle(id.toLong());
            OutputView.render(post);
        }catch (EntityNotFoundException e){
            articleNotFoundException(id, e);
        }
    }

    public String readDeleteId(){
        return InputView.readId("삭제");
    }

    public void delete(IdDto id) {
        try {
            postsService.delete(id.toLong());
            OutputView.renderDeleted(id.toLong());
        }catch (EntityNotFoundException e){
            articleNotFoundException(id, e);
        }
    }

    public String readUpdateId(){
        return InputView.readId("수정");
    }

    public PostInfoDto readUpdateInfo(IdDto id){
        return InputView.readUpdateInfo(id.toLong());
    }

    public void containId(IdDto id) {
        try {
            postsService.validateContainsId(id.toLong());
        }catch (EntityNotFoundException e){
            articleNotFoundException(id, e);
        }catch (IllegalArgumentException e){
            throw new PresentationException("게시글은 제목과 내용이 필수 입니다.",e);
        }
    }

    public void update(IdDto id, PostInfoDto article) {
        try {
            postsService.update(id.toLong(), article.title(), article.content());
            OutputView.renderUpdate(id.toLong());
        }catch (EntityNotFoundException e){
            articleNotFoundException(id, e);
        }catch (IllegalArgumentException e){
            throw new PresentationException("게시글은 제목과 내용이 필수 입니다.",e);
        }
    }

    private static void articleNotFoundException(IdDto id, RuntimeException e) {
        throw new PostNotFoundException(String.format("%s번 게시글은 존재하지 않습니다.", id.toLong()), e);
    }

}
