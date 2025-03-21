package org.example.account.domain.entity;

import org.example.persistance.anotaion.Id;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    @Id
    private Long id;

    private UserName userName ;
    private Email email;
    private Password password;
    private NickName nickName;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
    public User(Long id, UserName userName, Email email, Password password,NickName nickName) {
        this.id = id;
        this.userName = Objects.requireNonNull(userName);
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
        this.nickName = Objects.requireNonNull(nickName);
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    public User(UserName userName, Email email, Password password, NickName nickName) {
        this(null, userName, email, password, nickName);
    }

    public void update(Email email, Password password) {
        if(Objects.isNull(email) || Objects.isNull(password)) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        this.email = email;
        this.password = password;
        this.updateDate = LocalDateTime.now();
    }

    public Long id() {
        return id;
    }

    public String email() {
        return email.toStringValue();
    }

    public String userName() {
        return userName.toStringValue();
    }

    public LocalDateTime createDate() {
        return createDate;
    }

    public LocalDateTime updateDate() {
        return updateDate;
    }

    public String password() {
        return password.toStringValue();
    }

    public String nickName() {
        return nickName.toStringValue();
    }
}
