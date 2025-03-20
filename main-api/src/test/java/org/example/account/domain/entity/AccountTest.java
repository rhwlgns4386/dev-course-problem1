package org.example.account.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AccountTest {

    @Test
    void 회원생성() {
        UserName testId = new UserName("testId");
        Email email = new Email("test@naver.com");
        Password password = new Password("programmers");
        LocalDateTime now = LocalDateTime.now();

        User user = new User(testId, email, password);

        assertThat(user.email()).isEqualTo(email.toStringValue());
        assertThat(user.userName()).isEqualTo(testId.toStringValue());
        assertThat(user.createDate()).isAfterOrEqualTo(now);
        assertThat(user.password()).isEqualTo(password.toStringValue());
    }

    @Test
    void 회원정보_수정() {
        UserName userName = new UserName("userName");
        User user = new User(userName, new Email("test@naver.com"), new Password("programmers"));
        LocalDateTime now = LocalDateTime.now();

        Email email = new Email("test2@naver.com");
        Password password = new Password("programmers3");
        user.update(email, password);


        assertThat(user.email()).isEqualTo(email.toStringValue());
        assertThat(user.userName()).isEqualTo(userName.toStringValue());
        assertThat(user.updateDate()).isAfterOrEqualTo(now);
    }

    @Test
    void 회원정보수정에_null입력시_예외() {
        UserName userName = new UserName("userName");
        User user = new User(userName, new Email("test@naver.com"), new Password("programmers"));

        assertThatThrownBy(()->user.update(null, null)).isInstanceOf(IllegalArgumentException.class);
    }
}
