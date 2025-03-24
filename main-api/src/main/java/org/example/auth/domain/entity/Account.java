package org.example.auth.domain.entity;

import org.example.user.domain.entity.Password;

public class Account {

    private final Long id;
    private final String userId;
    private final String password;

    public Account(Long id,String userId, String password) {
        this.id = id;
        this.userId = userId;
        this.password = password;
    }

    public boolean samePassword(String password) {
        if(password.equals(this.password)) {
            return true;
        }
        return false;
    }

    public Long id() {
        return id;
    }
}
