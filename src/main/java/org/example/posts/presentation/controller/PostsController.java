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

    public PostInfoDto readInfo() {
        return InputView.readArticleInfo();
    }

    public void write(Long boardId, PostInfoDto postInfoDto) {
        try {
            postsService.save2(boardId, postInfoDto.title(), postInfoDto.content());
        } catch (EntityCreationException e) {
            throw new WriteException("게시글은 제목과 내용이 필수 입니다.", e);
        }
    }

    public void lookup(Long id) {
        try {
            Post post = postsService.loadArticle(id);
            OutputView.render(post);
        } catch (EntityNotFoundException e) {
            articleNotFoundException(id, e);
        }
    }

    public void delete(Long id) {
        try {
            postsService.delete(id);
            OutputView.renderDeleted(id);
        } catch (EntityNotFoundException e) {
            articleNotFoundException(id, e);
        }
    }

    public PostInfoDto readUpdateInfo(Long id) {
        return InputView.readUpdateInfo(id);
    }

    public void containId(Long id) {
        try {
            postsService.validateContainsId(id);
        } catch (EntityNotFoundException e) {
            articleNotFoundException(id, e);
        } catch (IllegalArgumentException e) {
            throw new PresentationException("게시글은 제목과 내용이 필수 입니다.", e);
        }
    }

    public void update(Long id, PostInfoDto article) {
        try {
            postsService.update(id, article.title(), article.content());
            OutputView.renderUpdate(id);
        } catch (EntityNotFoundException e) {
            articleNotFoundException(id, e);
        } catch (IllegalArgumentException e) {
            throw new PresentationException("게시글은 제목과 내용이 필수 입니다.", e);
        }
    }

    private static void articleNotFoundException(Long id, RuntimeException e) {
        throw new PostNotFoundException(String.format("%s번 게시글은 존재하지 않습니다.", id), e);
    }

}
