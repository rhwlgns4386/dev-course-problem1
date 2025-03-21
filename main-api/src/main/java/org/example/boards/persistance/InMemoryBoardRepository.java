package org.example.boards.persistance;

import org.example.boards.domain.entity.Board;
import org.example.boards.domain.service.BoardRepository;
import org.example.persistance.LongKeyBaseRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryBoardRepository extends LongKeyBaseRepository<Board> implements BoardRepository {
    @Override
    public Integer count() {
        return store.size();
    }

    @Override
    public List<Board> findByName(String name) {
        Collection<Board> values = store.values();
        return values.stream().filter(board -> board.title().equals(name)).collect(Collectors.toList());
    }
}
