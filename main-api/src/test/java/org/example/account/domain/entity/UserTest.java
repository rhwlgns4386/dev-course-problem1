package org.example.account.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class UserTest {

    @Test
    void 회원생성() {
        UserName testId = new UserName("testId");
        Email email = new Email("test@naver.com");
        Password password = new Password("programmers");
        NickName nickName = new NickName("test");
        LocalDateTime now = LocalDateTime.now();

        User user = new User(testId, email, password, nickName);

        assertThat(user.email()).isEqualTo(email.toStringValue());
        assertThat(user.userName()).isEqualTo(testId.toStringValue());
        assertThat(user.createDate()).isAfterOrEqualTo(now);
        assertThat(user.password()).isEqualTo(password.toStringValue());
        assertThat(user.nickName()).isEqualTo(nickName.toStringValue());
    }

    @Test
    void 회원정보_수정() {
        UserName userName = new UserName("userName");
        NickName nickName = new NickName("test");
        User user = new User(userName, new Email("test@naver.com"), new Password("programmers"), nickName);
        LocalDateTime now = LocalDateTime.now();

        Email email = new Email("test2@naver.com");
        Password password = new Password("programmers3");
        user.update(email, password);


        assertThat(user.email()).isEqualTo(email.toStringValue());
        assertThat(user.userName()).isEqualTo(userName.toStringValue());
        assertThat(user.updateDate()).isAfterOrEqualTo(now);
        assertThat(user.nickName()).isEqualTo(nickName.toStringValue());
    }

    @Test
    void 회원정보수정에_null입력시_예외() {
        UserName userName = new UserName("userName");
        User user = new User(userName, new Email("test@naver.com"), new Password("programmers"), new NickName("test"));

        assertThatThrownBy(() -> user.update(null, null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 회원정보검증() {
        UserName userName = new UserName("userName");
        Password password = new Password("programmers");
        User user = new User(userName, new Email("test@naver.com"), password, new NickName("test"));

        assertThat(user.samePassword(password)).isTrue();
    }

    @Test
    void 회원정보검증_시_다를경우_예외() {
        UserName userName = new UserName("userName");
        Password password = new Password("programmers");
        User user = new User(userName, new Email("test@naver.com"), password, new NickName("test"));

        assertThat(user.samePassword(new Password("2"))).isFalse();
    }
}
