package org.example.persistance;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<K,E> {
    Optional<E> findById(K id);

    E save(E element);

    List<E> findAll();

    void deleteById(K id);

    boolean extractById(K id);

    void clear();
}
