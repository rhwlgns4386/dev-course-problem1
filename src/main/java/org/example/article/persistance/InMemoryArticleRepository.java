package org.example.article.persistance;

import org.example.article.domain.entity.Article;
import org.example.article.domain.service.ArticleRepository;
import org.example.persistance.LongKeyBaseRepository;

public class InMemoryArticleRepository extends LongKeyBaseRepository<Article> implements ArticleRepository {

}
