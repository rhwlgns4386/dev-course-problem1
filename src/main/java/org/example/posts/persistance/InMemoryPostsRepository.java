package org.example.posts.persistance;

import org.example.posts.domain.entity.Post;
import org.example.posts.domain.service.PostsRepository;
import org.example.persistance.LongKeyBaseRepository;

public class InMemoryPostsRepository extends LongKeyBaseRepository<Post> implements PostsRepository {

}
