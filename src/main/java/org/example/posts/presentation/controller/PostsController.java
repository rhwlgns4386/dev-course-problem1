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

    public void write(IdDto boardId, PostInfoDto postInfoDto) {
        try {
            postsService.save2(boardId.toLong(), postInfoDto.title(), postInfoDto.content());
        } catch (EntityCreationException e) {
            throw new WriteException("게시글은 제목과 내용이 필수 입니다.", e);
        }
    }

    public void lookup(IdDto id) {
        try {
            Post post = postsService.loadArticle(id.toLong());
            OutputView.render(post);
        } catch (EntityNotFoundException e) {
            articleNotFoundException(id.toLong(), e);
        }
    }

    public void delete(IdDto id) {
        try {
            postsService.delete(id.toLong());
            OutputView.renderDeleted(id.toLong());
        } catch (EntityNotFoundException e) {
            articleNotFoundException(id.toLong(), e);
        }
    }

    public PostInfoDto readUpdateInfo(IdDto id) {
        return InputView.readUpdateInfo(id.toLong());
    }

    public void containId(IdDto id) {
        try {
            postsService.validateContainsId(id.toLong());
        } catch (EntityNotFoundException e) {
            articleNotFoundException(id.toLong(), e);
        } catch (IllegalArgumentException e) {
            throw new PresentationException("게시글은 제목과 내용이 필수 입니다.", e);
        }
    }

    public void update(IdDto id, PostInfoDto article) {
        try {
            postsService.update(id.toLong(), article.title(), article.content());
            OutputView.renderUpdate(id.toLong());
        } catch (EntityNotFoundException e) {
            articleNotFoundException(id.toLong(), e);
        } catch (IllegalArgumentException e) {
            throw new PresentationException("게시글은 제목과 내용이 필수 입니다.", e);
        }
    }

    private static void articleNotFoundException(Long id, RuntimeException e) {
        throw new PostNotFoundException(String.format("%s번 게시글은 존재하지 않습니다.", id), e);
    }

}
