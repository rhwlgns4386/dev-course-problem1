package org.example.auth.persentation.dto;

import org.example.user.domain.entity.Email;
import org.example.user.domain.entity.NickName;
import org.example.user.domain.entity.Password;
import org.example.user.domain.entity.UserName;

public class UserInfoDto {
    private String userName;
    private String password;
    private String nickName;
    private String email;

    public UserInfoDto(String userName, String password, String email, String nickName) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
    }

    public UserInfoDto(String password,String email) {
        this(null, password, email,null);
    }

    public UserName toUserName() {
        return new UserName(userName);
    }

    public Password password() {
        return new Password(password);
    }

    public NickName nickName() {
        return new NickName(nickName);
    }

    public Email email() {
        return new Email(email);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {
        return email;
    }
}
