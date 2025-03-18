package org.example.boards;

import org.example.ApplicationTest;
import org.example.CliApplication;
import org.example.boards.config.BoardConfig;
import org.example.boards.domain.entity.Board;
import org.example.boards.domain.entity.Title;
import org.example.boards.domain.service.BoardRepository;
import org.example.boards.persistance.InMemoryBoardRepository;
import org.example.boards.presentation.BoardRequestHandlerFactory;
import org.example.cli.runner.ApplicationStateHolder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
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
        ApplicationStateHolder.start();
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

    @Test
    void 게시판삭제(){
        boardRepository.save(new Board(new Title("test1")));

        run(()->{
            in("/boards/remove?boardId=1","exit");
            CliApplication.main(new String[]{});
        });
        Optional<Board> board = boardRepository.findById(1L);
        assertThat(board).isEmpty();
    }

    @Test
    void 게시판조회(){
        Title title = new Title("test1");
        boardRepository.save(new Board(title));

        run(()->{
            in("/boards/view?boardName=1","exit");
            CliApplication.main(new String[]{});
        });
        String format = title.createAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        assertThat(out()).contains("1/test1/"+format);
    }

    @Test
    void 없는_명령어_사용(){
        run(()->{
            in("/boards/view","exit");
            CliApplication.main(new String[]{});
        });
        assertThat(out()).contains("파라미터가 잘못 되었습니다.");
    }

    @Test
    void 없는_데이터_조회(){
        run(()->{
            in("/boards/view?boardName=1","exit");
            CliApplication.main(new String[]{});
        });
        assertThat(out()).contains("1번 게시판은 존재하지 않습니다.");
    }

    private static class TestConfig extends BoardConfig {

        @Override
        public BoardRepository boardRepository () {
            return boardRepository;
        }
    }
}
