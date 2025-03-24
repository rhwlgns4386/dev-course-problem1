package org.example.posts.domain.service;

import org.example.boards.domain.entity.Board;
import org.example.boards.domain.service.BoardRepository;
import org.example.posts.domain.entity.Actor;
import org.example.posts.domain.entity.Post;
import org.example.posts.domain.entity.Posts;
import org.example.global.exception.EntityCreationException;
import org.example.global.exception.EntityNotFoundException;

public class PostsService {

    private final PostsRepository postsRepository;
    private final BoardRepository boardRepository;
    private final ActorReader actorReader;

    public PostsService(PostsRepository postsRepository, BoardRepository boardRepository, ActorReader actorReader) {
        this.postsRepository = postsRepository;
        this.boardRepository = boardRepository;
        this.actorReader = actorReader;
    }

    public Post loadArticle(Long id) throws EntityNotFoundException {
        return findByIdOrElseThrow(id);
    }

    public Posts loadAll() {
        return new Posts(postsRepository.findAll());
    }

    public Long save(Long boardId, String title, String content, Long userId) throws EntityCreationException {
        try {
            Board board = boardRepository.findById(boardId).orElseThrow(() -> new EntityCreationException("엔티티를 생성 할 수 없습니다."));
            Post post = postsRepository.save(new Post(title, content, board, getActor(userId)));
            return post.id();
        } catch (IllegalArgumentException e) {
            throw new EntityCreationException("엔티티를 생성 할 수 없습니다.", e);
        }
    }

    private Actor getActor(Long userId) {
        Actor actor = new Actor();
        if(userId != null) {
            actor = actorReader.read(userId).orElseThrow(()-> new EntityNotFoundException("작성자를 찾을 수 없습니다."));
        }
        return actor;
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
