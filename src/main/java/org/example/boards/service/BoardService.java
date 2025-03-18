package org.example.boards.service;

import org.example.article.domain.exeption.EntityNotFoundException;
import org.example.boards.domain.entity.Board;
import org.example.boards.domain.entity.Title;

import java.util.Optional;

public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void save(Title title) {
        boardRepository.save(new Board(title));
    }

    public void update(Long id, Title title) {
        Board board = boardRepository.findById(id).orElseThrow(BoardService::entityNotFoundException);
        board.update(title);
    }

    public void delete(Long id) {
        if(!boardRepository.extractById(id)){
            throw entityNotFoundException();
        }
        boardRepository.deleteById(id);
    }

    private static EntityNotFoundException entityNotFoundException() {
        return new EntityNotFoundException("포스트를 찾을 수 없습니다.");
    }
}
