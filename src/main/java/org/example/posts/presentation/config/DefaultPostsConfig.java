package org.example.posts.presentation.config;

import org.example.boards.domain.service.BoardRepository;
import org.example.boards.persistance.InMemoryBoardRepository;
import org.example.posts.presentation.controller.PostsController;
import org.example.posts.domain.service.PostsRepository;
import org.example.posts.domain.service.PostsService;
import org.example.posts.persistance.InMemoryPostsRepository;

public class DefaultPostsConfig implements PostsConfig {

    @Override
    public PostsController commandController() {
        return new PostsController(articleService());
    }

    public PostsService articleService() {
        return new PostsService(articleRepository(), boardRepository());
    }

    public PostsRepository articleRepository() {
        return new InMemoryPostsRepository();
    }

    public BoardRepository boardRepository() {
        return new InMemoryBoardRepository();
    }
}
