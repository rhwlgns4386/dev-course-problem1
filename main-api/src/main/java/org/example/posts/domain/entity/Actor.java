package org.example.posts.domain.entity;

import org.example.account.domain.entity.User;

public class Actor {

    private Long userId;
    private String userName;

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
