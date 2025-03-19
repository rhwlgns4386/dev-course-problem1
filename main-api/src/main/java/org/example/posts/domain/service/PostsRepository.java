package org.example.posts.domain.service;

import org.example.persistance.BaseRepository;
import org.example.posts.domain.entity.Post;

public interface PostsRepository extends BaseRepository<Long, Post> {
}
