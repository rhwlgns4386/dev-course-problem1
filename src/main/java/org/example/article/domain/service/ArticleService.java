package org.example.article.domain.service;

import org.example.article.domain.entity.Article;
import org.example.article.domain.entity.Articles;
import org.example.article.domain.exeption.EntityCreationException;
import org.example.article.domain.exeption.EntityNotFoundException;

public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article loadArticle(Long id) throws EntityNotFoundException {
        return findByIdOrElseThrow(id);
    }

    public Articles loadAll() {
        return new Articles(articleRepository.findAll());
    }

    public void save(String title, String content) throws EntityCreationException {
        try {
            articleRepository.save(new Article(title,content));
        }catch (IllegalArgumentException e){
            throw new EntityCreationException("엔티티를 생성 할 수 없습니다.",e);
        }
    }

    public void update(Long id, String title, String content) throws EntityNotFoundException {
        Article article = findByIdOrElseThrow(id);
        article.update(title, content);
    }

    public void delete(Long id) throws EntityNotFoundException {
        if (!articleRepository.extractById(id)) throw entityNotFoundException();
        articleRepository.deleteById(id);
    }

    private Article findByIdOrElseThrow(Long id) throws EntityNotFoundException {
        return articleRepository.findById(id).orElseThrow(ArticleService::entityNotFoundException);
    }

    private static EntityNotFoundException entityNotFoundException() {
        return new EntityNotFoundException("아티클을 찾을 수 없습니다.");
    }
}
