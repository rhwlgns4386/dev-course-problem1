package org.example.persistance;

import org.example.article.domain.entity.Article;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<K,E> {
    Optional<E> findById(K id);

    void save(E article);

    List<E> findAll();

    void deleteById(K id);

    boolean extractById(K id);

    void clear();
}
