package org.example.posts.domain.service;

import org.example.posts.domain.entity.Post;
import org.example.posts.domain.entity.Posts;
import org.example.posts.domain.exeption.EntityCreationException;
import org.example.posts.domain.exeption.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PostServiceTest {

    private PostsRepository postsRepository;
    private PostsService postsService;

    @BeforeEach
    void initRepository() {
        postsRepository = new TestPostsRepository();
        postsRepository.save( new Post(1L, "title", "content"));

        postsService = new PostsService(postsRepository);
    }

    @ParameterizedTest
    @CsvSource(value = {"title,",",content"},delimiter = ',')
    void 저장시_제목혹은_컨텐츠가_비어있다면_예외(String title, String content){
        assertThatThrownBy(()-> postsService.save(title,content)).isInstanceOf(EntityCreationException.class);
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
        postsRepository.save(new Post(2L, "title", "content"));

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
    void  없는_게시글정수시_예외() {
        Long id = 2L;
        String title = "수정";
        String content = "수정되었습니다.";

        assertThatThrownBy(() -> postsService.update(id, title, content))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void 게시글삭제() {
        Long id = 2L;
        postsRepository.save(new Post(id, "title", "content"));

        postsService.delete(id);

        Optional<Post> article = postsRepository.findById(id);
        assertThat(article).isEmpty();
    }

    @Test
    void 없는_게시글삭제시_예외() {
        Long id = 2L;

        assertThatThrownBy(()-> postsService.delete(id))
                .isInstanceOf(EntityNotFoundException.class);
    }

    private static final class TestPostsRepository implements PostsRepository {
        private List<Post> posts = new ArrayList<>();

        @Override
        public Optional<Post> findById(Long id) {
            for (Post post : posts) {
                if(post.id().equals(id)) {
                    return Optional.of(post);
                }
            }
            return Optional.empty();
        }

        @Override
        public void save(Post post) {
           posts.add(post);
        }

        @Override
        public List<Post> findAll() {
            return posts;
        }

        @Override
        public void deleteById(Long id) {
            findById(id).ifPresent(posts::remove);
        }

        @Override
        public boolean extractById(Long id) {
            for (Post post : posts) {
                if(post.id().equals(id)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public void clear() {

        }
    }
}