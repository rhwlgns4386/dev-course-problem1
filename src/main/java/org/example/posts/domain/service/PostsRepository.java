package org.example.posts.domain.service;

import org.example.posts.domain.entity.Post;
import org.example.persistance.BaseRepository;

public interface PostsRepository extends BaseRepository<Long, Post> {
}
