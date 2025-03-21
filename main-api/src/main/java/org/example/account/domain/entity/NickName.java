package org.example.account.domain.entity;

public class NickName {

    private final String nickName;

    public NickName(String nickName) {
        this.nickName = nickName;
    }

    public String toStringValue() {
        return nickName;
    }
}
