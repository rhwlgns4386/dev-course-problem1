package org.example.boards.presentation.controller;

import org.example.boards.domain.entity.Title;
import org.example.boards.domain.service.BoardService;
import org.example.boards.presentation.exception.BoardNotFoundException;
import org.example.boards.presentation.view.InputView;
import org.example.boards.presentation.view.OutputView;
import org.example.global.exception.EntityNotFoundException;

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
        try {
            boardService.validateContainsId(id);
        }catch (EntityNotFoundException e){
            boardNotFoundException(id, e);
        }
    }

    public void edit(Long id, String title) {
        try {
            boardService.update(id, new Title(title));
        }catch (EntityNotFoundException e){
            boardNotFoundException(id, e);
        }
    }

    public void remove(Long id) {
        try {
            boardService.delete(id);
        } catch (EntityNotFoundException e) {
            boardNotFoundException(id, e);
        }
    }

    public void load(Long id) {
        try {
            OutputView.renderBoard(boardService.load(id));
        }catch (EntityNotFoundException e){
            boardNotFoundException(id, e);
        }
    }

    private static void boardNotFoundException(Long id, EntityNotFoundException e) {
        throw new BoardNotFoundException(String.format("%s번 게시판은 존재하지 않습니다.", id), e);
    }
}
