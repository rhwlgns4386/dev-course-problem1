package org.example.boards.domain.service;

import org.example.article.domain.exeption.EntityNotFoundException;
import org.example.boards.domain.entity.Board;
import org.example.boards.domain.entity.Title;

public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void save(Title title) {
        boardRepository.save(new Board(title));
    }

    public void update(Long id, Title title) throws EntityNotFoundException {
        Board board = boardRepository.findById(id).orElseThrow(BoardService::entityNotFoundException);
        board.update(title);
    }

    public void delete(Long id) throws EntityNotFoundException {
        validateContainsId(id);
        boardRepository.deleteById(id);
    }

    public Board load(Long id) throws EntityNotFoundException{
        return boardRepository.findById(id).orElseThrow(BoardService::entityNotFoundException);
    }

    public void validateContainsId(Long id) throws EntityNotFoundException{
        if(!boardRepository.extractById(id)){
            throw entityNotFoundException();
        }
    }

    private static EntityNotFoundException entityNotFoundException() {
        return new EntityNotFoundException("포스트를 찾을 수 없습니다.");
    }
}
