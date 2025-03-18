package org.example.boards.persistance;

import org.example.boards.domain.entity.Board;
import org.example.boards.domain.service.BoardRepository;
import org.example.persistance.LongKeyBaseRepository;

public class InMemoryBoardRepository extends LongKeyBaseRepository<Board> implements BoardRepository {
    @Override
    public Integer count() {
        return store.size();
    }
}
