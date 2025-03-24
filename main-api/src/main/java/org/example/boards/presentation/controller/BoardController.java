package org.example.boards.presentation.controller;

import org.example.boards.domain.entity.Board;
import org.example.boards.domain.entity.Title;
import org.example.boards.domain.service.BoardService;
import org.example.boards.presentation.exception.BoardNotFoundException;
import org.example.boards.presentation.view.InputView;
import org.example.boards.presentation.view.OutputView;
import org.example.global.exception.EntityNotFoundException;
import org.example.global.exception.PresentationException;

import java.util.List;

public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    public String readBoardName(){
        return InputView.readTitle();
    }

    public void write(String title){
        Long id = this.boardService.save(new Title(title));
        load(id);
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
            load(id);
        }catch (EntityNotFoundException e){
            boardNotFoundException(id, e);
        }
    }

    public void remove(Long id) {
        try {
            boardService.delete(id);
            OutputView.renderSuccessDelete();
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

    public void load(String name) {
        try {
            List<Board> boards = boardService.load(name);
            if(boards.isEmpty()){
                throw new PresentationException(name+" 이름의 게시판은 존재하지 않습니다.");
            }
            OutputView.renderBoardAll(boards);
        }catch (EntityNotFoundException e){
            boardNotFoundException(e);
        }
    }

    private static void boardNotFoundException(Long id, EntityNotFoundException e) {
        throw new BoardNotFoundException(String.format("%s번 게시판은 존재하지 않습니다.", id), e);
    }

    private static void boardNotFoundException(EntityNotFoundException e) {
        throw new BoardNotFoundException("게시판은 존재하지 않습니다.");
    }
}
