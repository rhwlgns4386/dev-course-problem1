package org.example.boards.presentation.controller;

import org.example.article.domain.exeption.EntityNotFoundException;
import org.example.article.presentation.dto.request.IdDto;
import org.example.boards.domain.entity.Board;
import org.example.boards.domain.entity.Title;
import org.example.boards.domain.service.BoardService;
import org.example.boards.presentation.view.InputView;
import org.example.boards.presentation.view.OutputView;

public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    public String readBoardName(){
        return InputView.readTitle();
    }

    public void write(String title){
        this.boardService.save(new Title(title));
    }

    public void containId(Long id) {
        boardService.validateContainsId(id);
    }

    public void edit(Long id, String title) {
        boardService.update(id, new Title(title));
    }

    public void remove(Long id) {
        boardService.delete(id);
    }

    public void load(Long id) {
        OutputView.renderBoard(boardService.load(id));
    }
}
