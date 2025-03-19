package org.example.posts.domain.service;

import org.example.posts.domain.entity.Post;
import org.example.posts.domain.entity.Posts;
import org.example.posts.domain.exeption.EntityCreationException;
import org.example.posts.domain.exeption.EntityNotFoundException;

public class PostsService {

    private final PostsRepository postsRepository;

    public PostsService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public Post loadArticle(Long id) throws EntityNotFoundException {
        return findByIdOrElseThrow(id);
    }

    public Posts loadAll() {
        return new Posts(postsRepository.findAll());
    }

    public void save(String title, String content) throws EntityCreationException {
        try {
            postsRepository.save(new Post(title,content));
        }catch (IllegalArgumentException e){
            throw new EntityCreationException("엔티티를 생성 할 수 없습니다.",e);
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
