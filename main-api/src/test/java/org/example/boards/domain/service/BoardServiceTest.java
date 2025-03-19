package org.example.boards.domain.service;

import org.example.boards.domain.entity.Board;
import org.example.boards.domain.entity.Title;
import org.example.posts.domain.exeption.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardServiceTest {

    private final BoardRepository boardRepository = new BoardRepositoryStub();
    private final BoardService boardService = new BoardService(boardRepository);
    private static final Title TITLE = new Title("기본");

    @BeforeEach
    public void initRepository(){
        boardRepository.clear();
        boardService.save(TITLE);
    }

    @Test
    void 보드를_생성한다(){
        boardRepository.clear();
        String title = "test";
        boardService.save(new Title(title));


        assertThat(boardRepository.count()).isEqualTo(1L);
        Optional<Board> byId = boardRepository.findById(1L);
        assertThat(byId).isPresent();
        byId.ifPresent((value)->assertThat(value.title()).isEqualTo(title));
    }

    @Test
    void 보드를_변경한다(){
        String title = "test";

        boardService.update(1L,new Title(title));

        assertThat(boardRepository.count()).isEqualTo(1L);
    }

    @Test
    void 보드를_없는_조회시_예외(){
        boardRepository.clear();
        assertThatThrownBy(()-> boardService.update(1L,new Title("update"))).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void 보드삭제(){
        boardService.delete(1L);

        assertThat(boardRepository.count()).isEqualTo(0L);
    }

    @Test
    void 없는_보드_삭제시_예외(){
        boardRepository.clear();
        assertThatThrownBy(()->boardService.delete(1L)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void 게시글목록_조회(){
        Board board = boardService.load(1L);
        assertThat(board.title()).isEqualTo(TITLE.toStringValue());
        assertThat(board.updateAt()).isEqualTo(TITLE.createAt());
    }

    private static class BoardRepositoryStub implements BoardRepository{

        private Board board;

        @Override
        public Board save(Board board){
            this.board = board;
            return board;
        }

        @Override
        public List<Board> findAll() {
            return List.of();
        }

        @Override
        public Integer count(){
            return board == null ? 0 : 1;
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

        @Override
        public void deleteById(Long id) {
            if(id != null && id == 1L && board != null){
                board = null;
            }
        }

        @Override
        public boolean extractById(Long id) {
            if(id != null && id == 1L && board != null){
                return true;
            }
            return false;
        }
    }
}
