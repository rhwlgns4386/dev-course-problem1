package org.example.boards;

import org.example.CliApplication;
import org.example.boards.config.BoardConfig;
import org.example.boards.domain.entity.Board;
import org.example.boards.domain.entity.Title;
import org.example.boards.domain.service.BoardRepository;
import org.example.boards.persistance.InMemoryBoardRepository;
import org.example.cli.test.CliApplicationTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardApplicationTest extends CliApplicationTest {

    private static BoardRepository boardRepository;

    @BeforeEach
    public void initRepository(){
        boardRepository = BoardConfig.boardRepository();
        boardRepository.clear();
    }

    @Test
    void 숫자가아닌_파라미터_예외() {
        run(() -> {
            in(input -> input.command("/boards/edit?boardId=1번"));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "숫자가 아닙니다."
        );
    }

    @Test
    void 게시판생성(){
        String title = "test";
        run(()->{
            in(input->input.command("/boards/add").input(title));
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
            in(input->input.command("/boards/edit?boardId=1").input(title));
            CliApplication.main(new String[]{});
        });
        assertThat(out()).contains("제목 : ");
        Optional<Board> board = boardRepository.findById(1L);
        assertThat(board).isPresent();
        board.ifPresent((value)->assertThat(value.title()).isEqualTo(title));
    }

    @Test
    void 게시판삭제(){
        Board save = boardRepository.save(new Board(new Title("test1")));

        run(()->{
            in(input->input.command("/boards/remove?boardId=1"));
            CliApplication.main(new String[]{});
        });
        Optional<Board> board = boardRepository.findById(save.id());
        assertThat(board).isEmpty();
    }

    @Test
    void 게시판조회(){
        Title title = new Title("test1");
        boardRepository.save(new Board(title));

        run(()->{
            in(input->input.command("/boards/view?boardName=test1"));
            CliApplication.main(new String[]{});
        });
        String format = title.createAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        assertThat(out()).contains("1/test1/"+format);
    }

    @Test
    void 게시판조회_중복_파라미터(){
        Title title = new Title("test1");
        boardRepository.save(new Board(title));

        run(()->{
            in(input->input.command("/boards/view?boardName=2&boardName=test1"));
            CliApplication.main(new String[]{});
        });
        String format = title.createAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        assertThat(out()).contains("1/test1/"+format);
    }

    @Test
    void 없는_명령어_사용(){
        run(()->{
            in(input->input.command("/boards/view"));
            CliApplication.main(new String[]{});
        });
        assertThat(out()).contains("파라미터가 잘못 되었습니다.");
    }

    @Test
    void 없는_데이터_조회(){
        run(()->{
            in(input->input.command("/boards/view?boardName=1번"));
            CliApplication.main(new String[]{});
        });
        assertThat(out()).contains("1번 이름의 게시판은 존재하지 않습니다.");
    }
}
