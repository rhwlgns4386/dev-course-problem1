package org.example.posts.domain.entity;

import java.util.List;
import java.util.Objects;

public class Posts {
    private final List<Post> posts;

    public Posts(List<Post> posts) {
        this.posts = posts;
    }

    public Integer size() {
        return posts.size();
    }

    public List<Post> articles() {
        return posts;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Posts posts1 = (Posts) o;
        for (Post post : posts) {
            if(!posts1.posts.contains(post)){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(posts);
    }
}
