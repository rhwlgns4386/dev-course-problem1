package org.example.posts;

import org.example.CliApplication;
import org.example.boards.domain.service.BoardRepository;
import org.example.boards.persistance.InMemoryBoardRepository;
import org.example.cli.test.CliApplicationTest;
import org.example.posts.domain.entity.Post;
import org.example.posts.domain.service.PostsRepository;
import org.example.posts.persistance.InMemoryPostsRepository;
import org.example.posts.presentation.command.CommandRequestHandlerFactory;
import org.example.posts.config.DefaultPostsConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.posts.TestBoards.board;

public class PostApplicationTest extends CliApplicationTest {

    private final static PostsRepository POSTS_REPOSITORY = new InMemoryPostsRepository();
    private final static BoardRepository BOARD_REPOSITORY = new InMemoryBoardRepository();

    @BeforeAll
    public static void initConfig() {
        BOARD_REPOSITORY.save(board);
        CommandRequestHandlerFactory.init(new TestConfig());
    }


    @Override
    @BeforeEach
    protected void init() {
        POSTS_REPOSITORY.clear();
        super.init();
    }

    @Test
    void 숫자가아닌_파라미터_예외() {
        run(() -> {
            in(input -> input.command("/posts/add?boardId=1번"));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "숫자가 아닙니다."
        );
    }

    @Test
    void 게시글_작성() {
        String title = "제목1";
        String content = "내용1";
        run(() -> {
            in(input -> input.command("/posts/add?boardId=1").input(title, content));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "제목 : "
                , "내용 : "
        );
    }

    @Test
    void 게시글_작성_예외() {

        run(() -> {
            in(input -> input.command("/posts/add?boardId=1").input(" ", " "));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "게시글은 제목과 내용이 필수 입니다."
        );
    }

    @Test
    void 게시글_작성_예외2() {
        Post post1 = new Post("제목1", "내용1", board);
        POSTS_REPOSITORY.save(post1);

        run(() -> {
            in(input -> input.command("/posts/add?boardId=1").input("1번", " ", " "));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "게시글은 제목과 내용이 필수 입니다."
        );
    }

    @Test
    void 게시글_조회() {
        Post post1 = new Post("제목1", "내용1", board);
        Post post2 = new Post("제목2", "내용2", board);
        POSTS_REPOSITORY.save(post1);
        POSTS_REPOSITORY.save(post2);
        run(() -> {
            in(input -> input.command("/posts/view?postId=1").command("/posts/view?postId=2"));
            CliApplication.main(new String[]{});
        });

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        assertThat(out()).contains(
                "1번 게시글",
                "제목 : 제목1",
                "작성일 : " + post1.createdDate().format(dateTimeFormatter),
                "수정일 : " + post1.updatedDate().format(dateTimeFormatter),
                "내용 : 내용1",
                "2번 게시글",
                "제목 : 제목2",
                "내용 : 내용2",
                "작성일 : " + post2.createdDate().format(dateTimeFormatter),
                "수정일 : " + post2.updatedDate().format(dateTimeFormatter)
        );
    }

    @Test
    void 없는_게시글_조회_시_예외() {
        Post post1 = new Post("제목1", "내용1", board);
        POSTS_REPOSITORY.save(post1);

        run(() -> {
            in((input) -> input.command("/posts/view?postId=2"));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "2번 게시글은 존재하지 않습니다"
        );
    }

    @Test
    void 없는_게시글_삭제() {
        Post post1 = new Post("제목1", "내용1", board);
        POSTS_REPOSITORY.save(post1);

        run(() -> {
            in((input) -> input.command("/posts/remove?postId=1"));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "1번 게시물이 성공적으로 삭제되었습니다!"
        );
    }

    @Test
    void 없는_게시글_삭제_시_예외() {
        Post post1 = new Post("제목1", "내용1", board);
        POSTS_REPOSITORY.save(post1);

        run(() -> {
            in(inputBuilder -> inputBuilder.command("/posts/remove?postId=2").input("2번"));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "2번 게시글은 존재하지 않습니다"
        );
    }

    @Test
    void 게시글_수정() {
        Post post1 = new Post("제목1", "내용1", board);
        POSTS_REPOSITORY.save(post1);

        run(() -> {
            in((input) -> input.command("/posts/edit?postId=1").input("제목2", "내용1"));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "1번 게시물을 수정합니다.",
                "제목 : ",
                "내용 : ",
                "1번 게시물이 성공적으로 수정되었습니다!"
        );
    }

    @Test
    void 없는_게시글_수정() {

        run(() -> {
            in((input -> input.command("/posts/edit?postId=1")));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "1번 게시글은 존재하지 않습니다"
        );
    }

    private static class TestConfig extends DefaultPostsConfig {
        @Override
        public PostsRepository articleRepository() {
            return POSTS_REPOSITORY;
        }

        @Override
        public BoardRepository boardRepository() {
            return BOARD_REPOSITORY;
        }
    }
}
