package org.example.boards.config;

import org.example.boards.domain.service.BoardRepository;
import org.example.boards.domain.service.BoardService;
import org.example.boards.persistance.InMemoryBoardRepository;
import org.example.boards.presentation.controller.BoardController;

public class BoardConfig {

    private static BoardRepository boardRepository;
    private static BoardService boardService;
    private static BoardController boardController;

    public static BoardController boardController(){
        if(boardController == null){
            boardController = new BoardController(boardService());
        }
        return boardController;
    }

    public static BoardService boardService(){
        if(boardService == null){
            boardService = new BoardService(boardRepository());
        }
        return boardService;
    }

    public static BoardRepository boardRepository(){
        if(boardRepository == null){
            boardRepository = new InMemoryBoardRepository();
        }
        return boardRepository;
    }

    public static void setBoardRepository(BoardRepository boardRepository){
        BoardConfig.boardRepository = boardRepository;
    }
}
