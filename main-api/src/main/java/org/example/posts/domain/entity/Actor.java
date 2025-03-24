package org.example.posts.domain.entity;

import org.example.user.domain.entity.User;

public class Actor {

    private final Long userId;
    private final String userName;

    public Actor(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Actor(User user) {
        this(user.id(), user.userName());
    }

    public Actor() {
        this(null, "비회원");
    }

    public String userName() {
        return userName;
    }
}
