package org.example.posts.domain.service;

import org.example.boards.domain.entity.Board;
import org.example.boards.domain.entity.Title;
import org.example.boards.domain.service.BoardRepository;
import org.example.boards.persistance.InMemoryBoardRepository;
import org.example.persistance.LongKeyBaseRepository;
import org.example.posts.domain.entity.Actor;
import org.example.posts.domain.entity.Post;
import org.example.posts.domain.entity.Posts;
import org.example.global.exception.EntityCreationException;
import org.example.global.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PostServiceTest {

    private PostsRepository postsRepository;
    private final static BoardRepository boardRepository = new InMemoryBoardRepository();
    private PostsService postsService;
    private static Board board;

    @BeforeAll
    public static void initBoard() {
        board = new Board(new Title("test"));
        boardRepository.save(board);
    }

    @BeforeEach
    void initRepository() {
        postsRepository = new TestPostsRepository();
        Actor actor = new Actor(1L,"testUser");
        postsRepository.save(new Post(1L, "title", "content", board, actor));

        postsService = new PostsService(postsRepository, boardRepository, id -> {
            if(id != 1L){
                return Optional.empty();
            }
            return Optional.of(actor);
        });
    }

    @Test
    void 게시글저장() {
        String title = "title";
        String content = "content";
        LocalDateTime createdDate = LocalDateTime.now();

        Long id = postsService.save(board.id(), title, content, null);
        Optional<Post> post = postsRepository.findById(id);
        assertThat(post).isPresent();
        post.ifPresent((value) -> {
            assertThat(value.id()).isEqualTo(id);
            assertThat(value.title()).isEqualTo(title);
            assertThat(value.content()).isEqualTo(content);
            assertThat(value.board()).isEqualTo(board);
            assertThat(value.createdDate()).isAfterOrEqualTo(createdDate);
        });
    }

    @ParameterizedTest
    @CsvSource(value = {"title,", ",content"}, delimiter = ',')
    void 저장시_제목혹은_컨텐츠가_비어있다면_예외(String title, String content) {
        assertThatThrownBy(() -> postsService.save(1L, title, content,1L)).isInstanceOf(EntityCreationException.class);
    }

    @Test
    void 없는_게시판에_작성시_비어있다면_예외() {
        assertThatThrownBy(() -> postsService.save(2L, "없다", "진짜없다", null)).isInstanceOf(EntityCreationException.class);
    }

    @Test
    void 저장된_게시글조회() {
        Long id = 1L;

        Post post = postsService.loadArticle(id);

        String title = "title";
        String content = "content";
        assertThat(post.id()).isEqualTo(id);
        assertThat(post.content()).isEqualTo(content);
        assertThat(post.title()).isEqualTo(title);
    }

    @Test
    void 없는_게시글_조회시_예외() {
        Long id = 2L;

        assertThatThrownBy(() -> postsService.loadArticle(id))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void 게시글_목록조회() {
        postsRepository.save(new Post(2L, "title", "content", board, new Actor()));

        Posts posts = postsService.loadAll();

        assertThat(posts.size()).isEqualTo(2);
        assertThat(posts).isEqualTo(new Posts(postsRepository.findAll()));
    }

    @Test
    void 게시글수정() {
        Long id = 1L;
        String title = "수정";
        String content = "수정되었습니다.";

        postsService.update(id, title, content);

        Post post = postsRepository.findById(1L).get();
        assertThat(post.id()).isEqualTo(id);
        assertThat(post.title()).isEqualTo(title);
        assertThat(post.content()).isEqualTo(content);
    }

    @Test
    void 없는_게시글정수시_예외() {
        Long id = 2L;
        String title = "수정";
        String content = "수정되었습니다.";

        assertThatThrownBy(() -> postsService.update(id, title, content))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void 게시글삭제() {
        Long id = 2L;
        postsRepository.save(new Post(id, "title", "content", board, new Actor()));

        postsService.delete(id);

        Optional<Post> article = postsRepository.findById(id);
        assertThat(article).isEmpty();
    }

    @Test
    void 없는_게시글삭제시_예외() {
        Long id = 2L;

        assertThatThrownBy(() -> postsService.delete(id))
                .isInstanceOf(EntityNotFoundException.class);
    }

    private static final class TestPostsRepository extends LongKeyBaseRepository<Post> implements PostsRepository {
    }
}