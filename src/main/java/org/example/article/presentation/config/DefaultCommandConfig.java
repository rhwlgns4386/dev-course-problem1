package org.example.article.presentation.config;

import org.example.article.presentation.controller.CommandController;
import org.example.article.domain.service.ArticleRepository;
import org.example.article.domain.service.ArticleService;
import org.example.article.persistance.InMemoryArticleRepository;

public class DefaultCommandConfig implements CommandConfig {

    @Override
    public CommandController commandController() {
        return new CommandController(articleService());
    }

    public ArticleService articleService(){
        return new ArticleService(articleRepository());
    }

    public ArticleRepository articleRepository(){
        return new InMemoryArticleRepository();
    }
}
