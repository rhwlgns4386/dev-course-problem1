package org.example.posts.persistance;

import org.example.persistance.LongKeyBaseRepository;
import org.example.posts.domain.entity.Post;
import org.example.posts.domain.service.PostsRepository;

public class InMemoryPostsRepository extends LongKeyBaseRepository<Post> implements PostsRepository {

}
