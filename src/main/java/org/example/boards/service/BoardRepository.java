package org.example.boards.service;

import org.example.boards.domain.entity.Board;

import java.util.Optional;

public interface BoardRepository {
    void save(Board board);

    Long count();

    Optional<Board> findById(Long id);

    void clear();
}
