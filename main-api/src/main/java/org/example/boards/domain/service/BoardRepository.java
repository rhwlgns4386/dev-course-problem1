package org.example.boards.domain.service;

import org.example.boards.domain.entity.Board;
import org.example.persistance.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends BaseRepository<Long, Board> {
    Integer count();

    List<Board> findByName(String name);
}
