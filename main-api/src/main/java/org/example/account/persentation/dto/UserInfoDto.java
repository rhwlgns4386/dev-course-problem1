package org.example.account.persentation.dto;

import org.example.account.domain.entity.Email;
import org.example.account.domain.entity.NickName;
import org.example.account.domain.entity.Password;
import org.example.account.domain.entity.UserName;

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
}
