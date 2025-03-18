package org.example.boards.domain.service;

import org.example.article.domain.exeption.EntityNotFoundException;
import org.example.boards.domain.entity.Board;
import org.example.boards.domain.entity.Title;
import org.example.boards.service.BoardRepository;
import org.example.boards.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardServiceTest {

    private final BoardRepository boardRepository = new BoardRepositoryStub();
    private final BoardService boardService = new BoardService(boardRepository);

    @BeforeEach
    public void initRepository(){
        boardRepository.clear();
    }

    @Test
    void 보드를_생성한다(){

        String title = "test";

        boardService.save(new Title(title));

        assertThat(boardRepository.count()).isEqualTo(1L);
        Optional<Board> byId = boardRepository.findById(1L);
        assertThat(byId).isPresent();
        byId.ifPresent((value)->assertThat(value.title()).isEqualTo(title));
    }

    @Test
    void 보드를_변경한다(){
        boardRepository.save(new Board(new Title("test")));
        String title = "test";

        boardService.update(1L,new Title(title));

        assertThat(boardRepository.count()).isEqualTo(1L);
    }

    @Test
    void 보드를_없는_조회시_예외(){
        assertThatThrownBy(()-> boardService.update(1L,new Title("update"))).isInstanceOf(EntityNotFoundException.class);
    }

    private static class BoardRepositoryStub implements BoardRepository{

        private Board board;

        @Override
        public void save(Board board){
            this.board = board;
        }

        @Override
        public Long count(){
            return board == null ? 0L : 1L;
        }

        @Override
        public Optional<Board> findById(Long id){
            if(id != null && id == 1L){
                return Optional.ofNullable(board);
            }

            return Optional.empty();
        }

        @Override
        public void clear(){
            board = null;
        }
    }
}
