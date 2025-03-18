package org.example.article.domain.service;

import org.example.article.domain.entity.Article;
import org.example.persistance.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends BaseRepository<Long, Article> {
}
