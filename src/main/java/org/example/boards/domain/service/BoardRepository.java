package org.example.boards.domain.service;

import org.example.boards.domain.entity.Board;
import org.example.persistance.BaseRepository;

public interface BoardRepository extends BaseRepository<Long, Board> {
    Integer count();
}
