package org.example.posts.config;

import org.example.auth.config.AuthConfig;
import org.example.boards.config.BoardConfig;
import org.example.boards.domain.service.BoardRepository;
import org.example.posts.domain.service.ActorReader;
import org.example.posts.domain.service.InMemoryActorReader;
import org.example.posts.domain.service.PostsRepository;
import org.example.posts.domain.service.PostsService;
import org.example.posts.persistance.InMemoryPostsRepository;
import org.example.posts.presentation.controller.PostsController;
import org.example.user.config.UserConfig;

public class DefaultPostsConfig implements PostsConfig {

    @Override
    public PostsController commandController() {
        return new PostsController(articleService());
    }

    public PostsService articleService() {
        return new PostsService(articleRepository(), boardRepository(),actorReader());
    }

    public PostsRepository articleRepository() {
        return new InMemoryPostsRepository();
    }

    public BoardRepository boardRepository() {
        return BoardConfig.boardRepository();
    }

    public ActorReader actorReader() {
        return new InMemoryActorReader(UserConfig.userRepository());
    }
}
