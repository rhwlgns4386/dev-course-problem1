package org.example.boards.config;

import org.example.boards.domain.service.BoardRepository;
import org.example.boards.domain.service.BoardService;
import org.example.boards.persistance.InMemoryBoardRepository;
import org.example.boards.presentation.controller.BoardController;

public class BoardConfig {

    public BoardController boardController(){
        return new BoardController(boardService());
    }

    public BoardService boardService(){
        return new BoardService(boardRepository());
    }

    public BoardRepository boardRepository(){
        return new InMemoryBoardRepository();
    }
}
