package org.example.posts.persistance;

import org.example.posts.TestBoards;
import org.example.posts.domain.entity.Post;
import org.example.posts.domain.service.PostsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.posts.TestBoards.board;

public class InMemoryPostsRepositoryTest {

    private PostsRepository postsRepository;

    @BeforeEach
    void setUp() {
        postsRepository = new InMemoryPostsRepository();
    }

    @Test
    void findById() {
        Post post = new Post("title", "content", board);

        postsRepository.save(post);

        Optional<Post> result = postsRepository.findById(1L);
        assertThat(result).isPresent();
        result.ifPresent((value)->{
            assertThat(value.title()).isEqualTo(post.title());
            assertThat(value.content()).isEqualTo(post.content());
        });
    }

    @Test
    void save() {
        Post post = new Post("title", "content",board);

        postsRepository.save(post);

        assertThat(post.id()).isEqualTo(1L);
    }

    @Test
    void findAll() {
        Post post = new Post("title", "content",board);
        Post post2 = new Post("title2", "content2",board);

        postsRepository.save(post);
        postsRepository.save(post2);

        List<Post> all = postsRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        assertThat(all).contains(post, post2);
    }

    @Test
    void deleteById() {
        Post post = new Post("title", "content",board);

        postsRepository.save(post);

        postsRepository.deleteById(1L);

        assertThat(postsRepository.findById(1L)).isEmpty();
    }

    @Test
    void extractById() {
        Post post = new Post("title", "content",board);

        postsRepository.save(post);

        assertThat(postsRepository.extractById(1L)).isTrue();
        assertThat(postsRepository.extractById(2L)).isFalse();
    }
}