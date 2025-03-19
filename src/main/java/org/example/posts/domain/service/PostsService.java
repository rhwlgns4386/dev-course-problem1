package org.example.posts.domain.service;

import org.example.boards.domain.entity.Board;
import org.example.boards.domain.entity.Title;
import org.example.boards.domain.service.BoardRepository;
import org.example.posts.domain.entity.Post;
import org.example.posts.domain.entity.Posts;
import org.example.posts.domain.exeption.EntityCreationException;
import org.example.posts.domain.exeption.EntityNotFoundException;

public class PostsService {

    private final PostsRepository postsRepository;
    private BoardRepository boardRepository;

    public PostsService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public PostsService(PostsRepository postsRepository, BoardRepository boardRepository) {
        this.postsRepository = postsRepository;
        this.boardRepository = boardRepository;
    }

    public Post loadArticle(Long id) throws EntityNotFoundException {
        return findByIdOrElseThrow(id);
    }

    public Posts loadAll() {
        return new Posts(postsRepository.findAll());
    }

    public void save(String title, String content) throws EntityCreationException {
        try {
            postsRepository.save(new Post(title, content, new Board(new Title("test"))));
        } catch (IllegalArgumentException e) {
            throw new EntityCreationException("엔티티를 생성 할 수 없습니다.", e);
        }
    }

    public Long save2(Long boardId, String title, String content) throws EntityCreationException {
        try {
            Board board = boardRepository.findById(boardId).orElseThrow(() -> new EntityCreationException("엔티티를 생성 할 수 없습니다."));
            Post post = postsRepository.save(new Post(title, content, board));
            return post.id();
        } catch (IllegalArgumentException e) {
            throw new EntityCreationException("엔티티를 생성 할 수 없습니다.", e);
        }
    }

    public void update(Long id, String title, String content) throws EntityNotFoundException {
        Post post = findByIdOrElseThrow(id);
        post.update(title, content);
    }

    public void delete(Long id) throws EntityNotFoundException {
        validateContainsId(id);
        postsRepository.deleteById(id);
    }

    public void validateContainsId(Long id) {
        if (!postsRepository.extractById(id)) throw entityNotFoundException();
    }

    private Post findByIdOrElseThrow(Long id) throws EntityNotFoundException {
        return postsRepository.findById(id).orElseThrow(PostsService::entityNotFoundException);
    }

    private static EntityNotFoundException entityNotFoundException() {
        return new EntityNotFoundException("아티클을 찾을 수 없습니다.");
    }
}
