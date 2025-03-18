package org.example.boards;

import org.example.ApplicationTest;
import org.example.CliApplication;
import org.example.boards.config.BoardConfig;
import org.example.boards.domain.entity.Board;
import org.example.boards.domain.entity.Title;
import org.example.boards.domain.service.BoardRepository;
import org.example.boards.persistance.InMemoryBoardRepository;
import org.example.boards.presentation.BoardRequestHandlerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardApplicationTest extends ApplicationTest {

    private static final BoardRepository boardRepository = new InMemoryBoardRepository();

    @BeforeAll
    public static void initConfig(){
        BoardRequestHandlerFactory.init(new TestConfig());
    }

    @BeforeEach
    public void initRepository(){
        boardRepository.clear();
    }

    @Test
    void 게시판생성(){
        String title = "test";
        run(()->{
            in("/boards/add",title,"exit");
            CliApplication.main(new String[]{});
        });
        assertThat(out()).contains("제목 : ");
        Optional<Board> board = boardRepository.findById(1L);
        assertThat(board).isPresent();
        board.ifPresent((value)->assertThat(value.title()).isEqualTo(title));
    }

    @Test
    void 게시판수정(){
        boardRepository.save(new Board(new Title("test1")));

        String title = "test";
        run(()->{
            in("/boards/edit?boardId=1",title,"exit");
            CliApplication.main(new String[]{});
        });
        assertThat(out()).contains("제목 : ");
        Optional<Board> board = boardRepository.findById(1L);
        assertThat(board).isPresent();
        board.ifPresent((value)->assertThat(value.title()).isEqualTo(title));
    }

    private static class TestConfig extends BoardConfig {

        @Override
        public BoardRepository boardRepository () {
            return boardRepository;
        }
    }
}
