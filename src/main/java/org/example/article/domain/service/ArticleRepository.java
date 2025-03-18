package org.example.article.domain.service;

import org.example.article.domain.entity.Article;
import org.example.persistance.BaseRepository;

public interface ArticleRepository extends BaseRepository<Long, Article> {
}
